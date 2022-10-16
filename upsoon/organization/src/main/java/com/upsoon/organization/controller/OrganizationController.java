package com.upsoon.organization.controller;


import com.upsoon.common.dto.NewOrganizationCreateDTO;
import com.upsoon.common.dto.NewOrganizationDTO;
import com.upsoon.organization.service.OrganizationService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/organization")
@Api(value = "Organization Controller")
public class OrganizationController {

    private final OrganizationService organizationSerice;

    public OrganizationController(OrganizationService organizationSerice) {
        this.organizationSerice = organizationSerice;
    }


    @PostMapping
    @Operation(summary = "Creates new organization by taking OrganizationDTO and RestaurantUserDTO")
    public ResponseEntity<NewOrganizationCreateDTO> createOrganization(@RequestBody NewOrganizationCreateDTO newOrganizationCreateDTO) {
        return organizationSerice.createOrganization(newOrganizationCreateDTO);
    }

    @PostMapping("/create-restaurant")
    @Operation(summary = "Creates a new organization and links to given organization")
    public ResponseEntity<NewOrganizationDTO> createRestaurant(@RequestParam(value = "organizationId") UUID organizationId, @RequestBody NewOrganizationDTO newOrganizationDTO) {
        return organizationSerice.createRestaurant(organizationId, newOrganizationDTO);
    }


    @GetMapping
    @Operation(summary = "Returns all of the organizations and restaurant for related restaurantUser")
    public ResponseEntity<Page<NewOrganizationDTO>> getAllOrganizations(@RequestParam(value = "restaurantUserId") UUID restaurantUserId, Pageable pageable) {
        return organizationSerice.getAllOrganizations(restaurantUserId, pageable);
    }


    @DeleteMapping
    public ResponseEntity<Void> deleteRestaurant(@RequestParam(value = "restaurantId") UUID restaurantId) {
        return organizationSerice.deleteRestaurant(restaurantId);
    }

}
