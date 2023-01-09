package com.upspoon.organization.service;

import com.upspoon.common.dto.Organization.NewOrganizationCreateDTO;
import com.upspoon.common.dto.Organization.NewOrganizationDTO;
import com.upspoon.common.dto.Organization.UpdateOrganizationDTO;
import com.upspoon.common.web.CustomPage;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

/**
 * @author burak.yesildal
 */


public interface OrganizationService {

    ResponseEntity<NewOrganizationCreateDTO> createOrganization(NewOrganizationCreateDTO newOrganizationCreateDTO);

    ResponseEntity<NewOrganizationDTO> createBusiness(UUID organizationId, NewOrganizationDTO newOrganizationDTO);

    ResponseEntity<CustomPage<NewOrganizationDTO>> getAllOrganizations(UUID restaurantUserId, Pageable pageable);

    ResponseEntity<Void> deleteRestaurant(UUID restaurantId);

    ResponseEntity<Void> linkUserToGivenRestaurant(UUID restaurantId, UUID userId);

    ResponseEntity<Void> unlinkGivenUserFromGivenRestaurant(UUID userId, UUID restaurantId);

    ResponseEntity<UpdateOrganizationDTO> updateRestaurant(UUID restaurantId, UpdateOrganizationDTO organizationDTO);
}
