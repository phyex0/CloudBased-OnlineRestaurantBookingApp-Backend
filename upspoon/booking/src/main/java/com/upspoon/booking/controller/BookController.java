package com.upspoon.booking.controller;

import com.upspoon.booking.service.BookService;
import com.upspoon.common.dto.Booking.CreateBookDetailDTO;
import com.upspoon.common.dto.Booking.UpdateOrganizationDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/delete-book-detail")
    public ResponseEntity<Void> deleteBookDetail(@RequestParam UUID businessId, @RequestParam UUID bookDetailId) {
        return bookService.deleteBookDetail(businessId, bookDetailId);
    }
}
