package com.upspoon.organization.controller;


import com.upspoon.common.dto.Organization.NewOrganizationCreateDTO;
import com.upspoon.common.dto.Organization.NewOrganizationDTO;
import com.upspoon.common.dto.Organization.UpdateOrganizationDTO;
import com.upspoon.common.web.CustomPage;
import com.upspoon.organization.service.OrganizationService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author burak.yesildal
 */

@RestController
@RequestMapping("/api/organization")
public class OrganizationController {

    private final OrganizationService organizationSerice;

    public OrganizationController(OrganizationService organizationSerice) {
        this.organizationSerice = organizationSerice;
    }


    @PostMapping
    public ResponseEntity<NewOrganizationCreateDTO> createOrganization(@RequestBody NewOrganizationCreateDTO newOrganizationCreateDTO) {
        return organizationSerice.createOrganization(newOrganizationCreateDTO);
    }

    @PostMapping("/create-business")
    public ResponseEntity<NewOrganizationDTO> createBusiness(@RequestParam(value = "organizationId") UUID organizationId, @RequestBody NewOrganizationDTO newOrganizationDTO) {
        return organizationSerice.createBusiness(organizationId, newOrganizationDTO);
    }


    @GetMapping
    public ResponseEntity<CustomPage<NewOrganizationDTO>> getAllOrganizations(@RequestParam(value = "restaurantUserId") UUID restaurantUserId, Pageable pageable) {
        return organizationSerice.getAllOrganizations(restaurantUserId, pageable);
    }


    @DeleteMapping
    public ResponseEntity<Void> deleteRestaurant(@RequestParam(value = "restaurantId") UUID restaurantId) {
        return organizationSerice.deleteRestaurant(restaurantId);
    }

    @GetMapping("/link-user-to-given-restaurant")
    public ResponseEntity<Void> linkUserToGivenRestaurant(@RequestParam(value = "restaurantId") UUID restaurantId, @RequestParam(value = "userId") UUID userId) {
        return organizationSerice.linkUserToGivenRestaurant(restaurantId, userId);
    }

    @DeleteMapping("/unlink-user-from-given-restaurant")
    public ResponseEntity<Void> unlinkGivenUserFromGivenRestaurant(@RequestParam(value = "userId") UUID userId, @RequestParam(value = "restaurantId") UUID restaurantId) {
        return organizationSerice.unlinkGivenUserFromGivenRestaurant(userId, restaurantId);
    }

    @PutMapping
    public ResponseEntity<UpdateOrganizationDTO> updateRestaurant(@RequestParam(value = "restaurantId") UUID restaurantId, @RequestBody UpdateOrganizationDTO organizationDTO) {
        return organizationSerice.updateRestaurant(restaurantId, organizationDTO);
    }

}
