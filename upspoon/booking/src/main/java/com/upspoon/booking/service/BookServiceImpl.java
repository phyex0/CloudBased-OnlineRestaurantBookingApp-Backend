package com.upspoon.booking.service;

import com.upspoon.booking.mapper.*;
import com.upspoon.booking.model.Book;
import com.upspoon.booking.model.BookDetails;
import com.upspoon.booking.model.Organization;
import com.upspoon.booking.producer.KafkaProducer;
import com.upspoon.booking.repository.OrganizationRepository;
import com.upspoon.common.dto.Booking.BookDTO;
import com.upspoon.common.dto.Booking.BookDetailDTO;
import com.upspoon.common.dto.Booking.CreateBookDetailDTO;
import com.upspoon.common.dto.Booking.UpdateOrganizationDTO;
import com.upspoon.common.exceptions.AlreadyBookedTableException;
import com.upspoon.common.exceptions.BookingFailedException;
import com.upspoon.common.exceptions.BookingOrganizationNotFound;
import com.upspoon.common.exceptions.BusinessNotFoundException;
import com.upspoon.common.kafkaTemplateDTO.OrganizationToBooking;
import com.upspoon.common.web.CustomPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * @author burak.yesildal
 */

@Service
public class BookServiceImpl implements BookService {

    private final OrganizationToBookingMapper organizationToBookingMapper;

    private final OrganizationRepository organizationRepository;

    private final KafkaProducer kafkaProducer;

    private final UpdateOrganizationMapper updateOrganizationMapper;

    private final BookDetailsDTOToBookDetailsMapper bookDetailsDTOToBookDetailsMapper;

    private final BookDTOtoBookMapper bookDTOtoBookMapper;

    private final BookDetailsDTOMapper bookDetailsDTOMapper;

    public BookServiceImpl(OrganizationToBookingMapper organizationToBookingMapper, OrganizationRepository organizationRepository, KafkaProducer kafkaProducer, UpdateOrganizationMapper updateOrganizationMapper, BookDetailsDTOToBookDetailsMapper bookDetailsDTOToBookDetailsMapper, BookDTOtoBookMapper bookDTOtoBookMapper, BookDetailsDTOMapper bookDetailsDTOMapper) {
        this.organizationToBookingMapper = organizationToBookingMapper;
        this.organizationRepository = organizationRepository;
        this.kafkaProducer = kafkaProducer;
        this.updateOrganizationMapper = updateOrganizationMapper;
        this.bookDetailsDTOToBookDetailsMapper = bookDetailsDTOToBookDetailsMapper;
        this.bookDTOtoBookMapper = bookDTOtoBookMapper;
        this.bookDetailsDTOMapper = bookDetailsDTOMapper;
    }

    @Override
    @Transactional
    public void createOrganization(OrganizationToBooking organizationToBooking) {
        var organization = organizationRepository.findByExactOrganizationId(organizationToBooking.getOrganizationId());
        if (!Objects.isNull(organization)) {
            kafkaProducer.produce(organizationToBooking);
            return;
        }
        organization = organizationToBookingMapper.toEntity(organizationToBooking);
        organizationRepository.save(organization);

    }

    @Override
    @Transactional
    public ResponseEntity<UpdateOrganizationDTO> updateOrganization(UpdateOrganizationDTO updateOrganizationDTO) {
        var organization = organizationRepository.findByExactOrganizationId(updateOrganizationDTO.getOrganizationId());

        if (Objects.isNull(organization)) {
            throw new BookingOrganizationNotFound("Organization can't updated.");
        }

        organization = updateOrganizationMapper.updateEntity(updateOrganizationDTO, organization);
        organizationRepository.save(organization);
        //TODO: update organization from Organization service as well.

        return new ResponseEntity<>(updateOrganizationDTO, HttpStatus.OK);
    }


    @Override
    @Transactional
    public ResponseEntity<CreateBookDetailDTO> createBookDetail(UUID businessId, CreateBookDetailDTO createBookDetailDTO) {


        Organization organization = organizationRepository.findByExactOrganizationId(businessId);

        if (Objects.isNull(organization))
            throw new BookingOrganizationNotFound("Business is not found, booking failed");

        BookDetails bookDetails = bookDetailsDTOToBookDetailsMapper.toEntity(createBookDetailDTO);
        bookDetails.setBookDate(setToFirstHourOfDay(bookDetails.getBookDate()));

        AtomicReference<Boolean> reserved = new AtomicReference<>(Boolean.FALSE);
        organization.getBookList().forEach(book -> {
            if (book.getDate().toInstant().equals(bookDetails.getBookDate().toInstant())) {
                Set<Integer> guests = book.getBookDetailsList().stream().map(d -> d.getTableNumber()).collect(Collectors.toSet());
                if (guests.contains(bookDetails.getTableNumber()))
                    throw new AlreadyBookedTableException("This table already booked. Try another table");
                book.getBookDetailsList().add(bookDetails);
                reserved.set(Boolean.TRUE);
            }
        });

        if (!reserved.get())
            throw new BookingFailedException("Booking Failed. Try again Later");
        organizationRepository.save(organization);

        return new ResponseEntity<>(createBookDetailDTO, HttpStatus.OK);
    }

    private Date setToFirstHourOfDay(Date date) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        localDate = LocalDate.from(localDate.atStartOfDay());
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    private Integer getDateDifferenceAsDay(Date startDate, Date endDate) {
        Period diff = Period.between(startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        return diff.getDays() > 0 ? diff.getDays() : 0;
    }

    private Date incrementDateOne(Date date) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        localDate = localDate.plusDays(1);
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    @Override
    @Transactional
    public ResponseEntity<Void> enableBookingForGivenAmountOfDay(UUID restaurantId, Integer day) {
        Organization organization = organizationRepository.findByExactOrganizationId(restaurantId);
        if (Objects.isNull(organization))
            throw new BookingOrganizationNotFound("Business is not found!");
        Collections.sort(organization.getBookList(), Comparator.comparing(Book::getDate));
        Date date = this.setToFirstHourOfDay(new Date());

        int i = 0;
        if (!organization.getBookList().isEmpty()) {
            var lastBook = organization.getBookList().get(organization.getBookList().size() - 1);
            if (lastBook.getDate().after(date)) {
                i = getDateDifferenceAsDay(date, lastBook.getDate());
                date = lastBook.getDate();
            }
        }

        for (; i < day; i++) {
            date = incrementDateOne(date);
            Book b = new Book(date, new ArrayList<>());
            organization.getBookList().add(b);
        }
        organizationRepository.save(organization);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @Override
    @Transactional
    public ResponseEntity<Void> deleteBook(UUID businessId, UUID bookId) {

        Organization organization = organizationRepository.findByExactOrganizationId(businessId);

        if (Objects.isNull(organization))
            throw new BookingOrganizationNotFound("Business is not found!");

        int initial = organization.getBookList().size();
        organization.getBookList().removeIf(b -> b.getId().equals(bookId));
        if (initial == organization.getBookList().size())
            throw new BookingFailedException("Book is not found!");
        organizationRepository.save(organization);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @Override
    public ResponseEntity<CustomPage<BookDTO>> getBooksForBusiness(UUID businessId, Date date, Pageable pageable) {
        Date now = setToFirstHourOfDay(new Date());

        Page<Book> books = organizationRepository.getBooks(businessId, now, date, pageable);
        List<BookDTO> bookDTOS = bookDTOtoBookMapper.toDto(books.getContent());

        return new ResponseEntity<>(new CustomPage<>(bookDTOS, pageable, books.getTotalElements()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CustomPage<BookDetailDTO>> getBookDetailsForBusiness(UUID businessId, UUID bookId, Pageable pageable) {
        Page<BookDetails> bookDetails = organizationRepository.getBookDetails(businessId, bookId, pageable);
        List<BookDetailDTO> bookDetailDTOS = bookDetailsDTOMapper.toDto(bookDetails.getContent());

        return new ResponseEntity<>(new CustomPage<>(bookDetailDTOS, pageable, bookDetails.getTotalElements()), HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<Void> cancelBooking(UUID businessId, UUID bookDetailId) {
        Organization organization = organizationRepository.findByExactOrganizationId(businessId);

        if (Objects.isNull(organization))
            throw new BusinessNotFoundException("Business is not found!");
        AtomicReference<Boolean> isDeleted = new AtomicReference<>(Boolean.FALSE);
        organization.getBookList().stream().forEach(b -> {
            int initial = b.getBookDetailsList().size();
            b.getBookDetailsList().removeIf(bd -> bd.getId().equals(bookDetailId));
            if (initial != b.getBookDetailsList().size())
                isDeleted.set(Boolean.TRUE);
        });

        if (!isDeleted.get())
            throw new BookingFailedException("Given record is not found!");
        organizationRepository.save(organization);

        return new ResponseEntity<>(HttpStatus.OK);

    }
}
