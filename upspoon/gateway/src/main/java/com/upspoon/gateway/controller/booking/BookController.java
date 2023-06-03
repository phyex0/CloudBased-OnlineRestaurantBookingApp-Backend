package com.upspoon.gateway.controller.booking;

import com.upspoon.common.dto.Booking.BookDTO;
import com.upspoon.common.dto.Booking.BookDetailDTO;
import com.upspoon.common.dto.Booking.CreateBookDetailDTO;
import com.upspoon.common.dto.Booking.UpdateOrganizationDTO;
import com.upspoon.common.web.CustomPage;
import com.upspoon.gateway.client.booking.BookClient;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

/**
 * @author burak.yesildal
 */

@RestController
@RequestMapping("/book/api")
@AllArgsConstructor
public class BookController {

    private final BookClient bookClient;


    @PutMapping
    @PreAuthorize("hasPermission('ROLE', 'ORGANIZATION_ROLE') or hasPermission('ROLE','ADMIN_ROLE')")
    @Operation(summary = "Update Business")
    public ResponseEntity<UpdateOrganizationDTO> updateOrganization(@RequestBody UpdateOrganizationDTO updateOrganizationDTO) {
        return bookClient.updateOrganization(updateOrganizationDTO);
    }

    @PostMapping("/create-book-detail")
    //TODO userRole required?
    @Operation(summary = "User makes a reservation for the table")
    public ResponseEntity<CreateBookDetailDTO> createBookDetail(@RequestParam UUID businessId, @RequestBody CreateBookDetailDTO createBookDetailDTO) {
        return bookClient.createBookDetail(businessId, createBookDetailDTO);
    }

    @GetMapping("/enable-booking-for-given-amount-of-day")
    @PreAuthorize("hasPermission('ROLE', 'ORGANIZATION_ROLE') or hasPermission('ROLE','ADMIN_ROLE')")
    @Operation(summary = "Business owner can open reservation for upcoming days")
    public ResponseEntity<Void> enableBookingForGivenAmountOfDay(@RequestParam UUID restaurantId, @RequestParam Integer day) {
        return bookClient.enableBookingForGivenAmountOfDay(restaurantId, day);
    }

    @DeleteMapping("/delete-book")
    @PreAuthorize("hasPermission('ROLE', 'ORGANIZATION_ROLE') or hasPermission('ROLE','ADMIN_ROLE')")
    @Operation(summary = "Delete selected book and corresponding reservations")
    public ResponseEntity<Void> deleteBookDetail(@RequestParam UUID businessId, @RequestParam UUID bookId) {
        return bookClient.deleteBook(businessId, bookId);
    }

    @GetMapping("/get-books-for-business")
    @Operation(summary = "Users can monitor available days of selected business")
    public ResponseEntity<CustomPage<BookDTO>> getBooksForBusiness(@RequestParam UUID businessId, @RequestParam(required = false) LocalDate date, Pageable pageable) {
        return bookClient.getBooksForBusiness(businessId, date, pageable);
    }

    @GetMapping("/get-book-details-for-business")
    @Operation(summary = "Users can view selected business detail like how many seats are empty.")
    public ResponseEntity<CustomPage<BookDetailDTO>> getBookDetailsForBusiness(@RequestParam UUID businessId, @RequestParam UUID userId, Pageable pageable) {
        return bookClient.getBookDetailsForBusiness(businessId, userId, pageable);
    }

    @DeleteMapping("/cancel-book-detail")
    @PreAuthorize("hasPermission('ROLE', 'ORGANIZATION_ROLE') or hasPermission('ROLE','ADMIN_ROLE')")
    @Operation(summary = "Business owner can cancel the book detail")
    public ResponseEntity<Void> cancelBooking(@RequestParam UUID businessId, @RequestParam UUID userId) {
        return bookClient.cancelBooking(businessId, userId);
    }

}
