package com.upspoon.booking.service;

import com.upspoon.common.dto.Booking.BookDTO;
import com.upspoon.common.dto.Booking.BookDetailDTO;
import com.upspoon.common.dto.Booking.CreateBookDetailDTO;
import com.upspoon.common.dto.Booking.UpdateOrganizationDTO;
import com.upspoon.common.kafkaTemplateDTO.OrganizationToBooking;
import com.upspoon.common.web.CustomPage;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

/**
 * @author burak.yesildal
 */

public interface BookService {

    void createOrganization(OrganizationToBooking organizationToBooking);

    ResponseEntity<UpdateOrganizationDTO> updateOrganization(UpdateOrganizationDTO updateOrganizationDTO);

    ResponseEntity<CreateBookDetailDTO> createBookDetail(UUID businessId, CreateBookDetailDTO createBookDetailDTO);

    ResponseEntity<Void> enableBookingForGivenAmountOfDay(UUID restaurantId, Integer day);

    ResponseEntity<Void> deleteBook(UUID businessId, UUID bookId);

    ResponseEntity<CustomPage<BookDTO>> getBooksForBusiness(UUID businessId, LocalDate date, Pageable pageable);

    ResponseEntity<CustomPage<BookDetailDTO>> getBookDetailsForBusiness(UUID businessId, UUID userId, Pageable pageable);

    ResponseEntity<Void> cancelBooking(UUID businessId, UUID userId);
}
