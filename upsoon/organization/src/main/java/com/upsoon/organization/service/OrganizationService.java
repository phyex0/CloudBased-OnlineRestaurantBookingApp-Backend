package com.upsoon.organization.service;

import com.upsoon.common.dto.NewOrganizationCreateDTO;
import com.upsoon.common.dto.NewOrganizationDTO;
import com.upsoon.common.dto.UpdateOrganizationDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

/**
 * @author Halit Burak Yeşildal
 */


public interface OrganizationService {

    ResponseEntity<NewOrganizationCreateDTO> createOrganization(NewOrganizationCreateDTO newOrganizationCreateDTO);

    ResponseEntity<NewOrganizationDTO> createRestaurant(UUID organizationId, NewOrganizationDTO newOrganizationDTO);

    ResponseEntity<Page<NewOrganizationDTO>> getAllOrganizations(UUID restaurantUserId, Pageable pageable);

    ResponseEntity<Void> deleteRestaurant(UUID restaurantId);

    ResponseEntity<Void> linkUserToGivenRestaurant(UUID restaurantId, UUID userId);

    ResponseEntity<Void> unlinkGivenUserFromGivenRestaurant(UUID userId, UUID restaurantId);

    ResponseEntity<UpdateOrganizationDTO> updateRestaurant(UUID restaurantId, UpdateOrganizationDTO organizationDTO);
}
