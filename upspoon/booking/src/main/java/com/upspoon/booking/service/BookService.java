package com.upspoon.booking.service;

import com.upspoon.common.dto.Booking.CreateBookDetailDTO;
import com.upspoon.common.dto.Booking.UpdateOrganizationDTO;
import com.upspoon.common.kafkaTemplateDTO.OrganizationToBooking;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

/**
 * @author burak.yesildal
 */

public interface BookService {

    void createOrganization(OrganizationToBooking organizationToBooking);

    ResponseEntity<UpdateOrganizationDTO> updateOrganization(UpdateOrganizationDTO updateOrganizationDTO);

    ResponseEntity<CreateBookDetailDTO> createBookDetail(UUID businessId, CreateBookDetailDTO createBookDetailDTO);

    ResponseEntity<Void> enableBookingForGivenAmountOfDay(UUID restaurantId, Integer day);

    ResponseEntity<Void> deleteBookDetail(UUID businessId, UUID bookDetailId);
}
