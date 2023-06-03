package com.upspoon.booking.controller;

import com.upspoon.booking.service.BookService;
import com.upspoon.common.dto.Booking.BookDTO;
import com.upspoon.common.dto.Booking.BookDetailDTO;
import com.upspoon.common.dto.Booking.CreateBookDetailDTO;
import com.upspoon.common.dto.Booking.UpdateOrganizationDTO;
import com.upspoon.common.web.CustomPage;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

/**
 * @author burak.yesildal
 */

@RestController
@RequestMapping("/api/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PutMapping
    public ResponseEntity<UpdateOrganizationDTO> updateOrganization(@RequestBody UpdateOrganizationDTO updateOrganizationDTO) {
        return bookService.updateOrganization(updateOrganizationDTO);
    }

    @PostMapping("/create-book-detail")
    public ResponseEntity<CreateBookDetailDTO> createBookDetail(@RequestParam UUID businessId, @RequestBody CreateBookDetailDTO createBookDetailDTO) {
        return bookService.createBookDetail(businessId, createBookDetailDTO);
    }

    @GetMapping("/enable-booking-for-given-amount-of-day")
    public ResponseEntity<Void> enableBookingForGivenAmountOfDay(@RequestParam UUID restaurantId, @RequestParam Integer day) {
        return bookService.enableBookingForGivenAmountOfDay(restaurantId, day);
    }

    @DeleteMapping("/delete-book")
    public ResponseEntity<Void> deleteBook(@RequestParam UUID businessId, @RequestParam UUID bookId) {
        return bookService.deleteBook(businessId, bookId);
    }

    @GetMapping("/get-books-for-business")
    public ResponseEntity<CustomPage<BookDTO>> getBooksForBusiness(@RequestParam UUID businessId, @RequestParam(required = false) LocalDate date, Pageable pageable) {
        return bookService.getBooksForBusiness(businessId, date, pageable);
    }

    @GetMapping("/get-book-details-for-business")
    public ResponseEntity<CustomPage<BookDetailDTO>> getBookDetailsForBusiness(@RequestParam UUID businessId, @RequestParam UUID userId, Pageable pageable) {
        return bookService.getBookDetailsForBusiness(businessId, userId, pageable);
    }

    @DeleteMapping("/cancel-book-detail")
    public ResponseEntity<Void> cancelBooking(@RequestParam UUID businessId, @RequestParam UUID userId) {
        return bookService.cancelBooking(businessId, userId);
    }

}
