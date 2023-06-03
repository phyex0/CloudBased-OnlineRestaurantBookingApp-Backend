package com.upspoon.gateway.client.booking;

import com.upspoon.common.config.CustomFeignConfiguration;
import com.upspoon.common.dto.Booking.BookDTO;
import com.upspoon.common.dto.Booking.BookDetailDTO;
import com.upspoon.common.dto.Booking.CreateBookDetailDTO;
import com.upspoon.common.dto.Booking.UpdateOrganizationDTO;
import com.upspoon.common.web.CustomPage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

/**
 * @author burak.yesildal
 */

@FeignClient(name = "${client.booking-api.name}", url = "${client.booking-api.url}", configuration = CustomFeignConfiguration.class)
public interface BookClient {
    @PutMapping
    ResponseEntity<UpdateOrganizationDTO> updateOrganization(@RequestBody UpdateOrganizationDTO updateOrganizationDTO);

    @PostMapping("/api/book/create-book-detail")
    ResponseEntity<CreateBookDetailDTO> createBookDetail(@RequestParam UUID businessId, @RequestBody CreateBookDetailDTO createBookDetailDTO);

    @GetMapping("/api/book/enable-booking-for-given-amount-of-day")
    ResponseEntity<Void> enableBookingForGivenAmountOfDay(@RequestParam UUID restaurantId, @RequestParam Integer day);

    @DeleteMapping("/api/book/delete-book")
    ResponseEntity<Void> deleteBook(@RequestParam UUID businessId, @RequestParam UUID bookId);

    @GetMapping("/api/book/get-books-for-business")
    ResponseEntity<CustomPage<BookDTO>> getBooksForBusiness(@RequestParam UUID businessId, @RequestParam(required = false) LocalDate date, Pageable pageable);

    @GetMapping("/api/book/get-book-details-for-business")
    ResponseEntity<CustomPage<BookDetailDTO>> getBookDetailsForBusiness(@RequestParam UUID businessId, @RequestParam UUID userId, Pageable pageable);


    @DeleteMapping("/api/book/cancel-book-detail")
    ResponseEntity<Void> cancelBooking(@RequestParam UUID businessId, @RequestParam UUID userId);
}
