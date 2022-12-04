package com.upsoon.gateway.controller.organization;


import com.upsoon.common.dto.Organization.NewOrganizationCreateDTO;
import com.upsoon.common.dto.Organization.NewOrganizationDTO;
import com.upsoon.common.dto.Organization.UpdateOrganizationDTO;
import com.upsoon.common.web.CustomPage;
import com.upsoon.gateway.client.organization.OrganizationClient;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author Halit Burak Yeşildal
 */

@RestController
@RequestMapping("/organization/api")
@Api(value = "Organization Controller")
public class OrganizationController {

    private final OrganizationClient organizationClient;

    public OrganizationController(OrganizationClient organizationClient) {
        this.organizationClient = organizationClient;
    }

    @PostMapping
    @Operation(summary = "Creates new organization by taking OrganizationDTO and RestaurantUserDTO")
    public ResponseEntity<NewOrganizationCreateDTO> createOrganization(@RequestBody NewOrganizationCreateDTO newOrganizationCreateDTO) {
        return organizationClient.createOrganization(newOrganizationCreateDTO);
    }

    @PostMapping("/create-restaurant")
    @Operation(summary = "Creates a new organization and links to given organization")
    public ResponseEntity<NewOrganizationDTO> createRestaurant(@RequestParam(value = "organizationId") UUID organizationId, @RequestBody NewOrganizationDTO newOrganizationDTO) {
        return organizationClient.createRestaurant(organizationId, newOrganizationDTO);
    }


    @GetMapping
    @Operation(summary = "Returns all of the organizations and restaurant for related restaurantUser")
    public ResponseEntity<CustomPage<NewOrganizationDTO>> getAllOrganizations(@RequestParam(value = "restaurantUserId") UUID restaurantUserId, Pageable pageable) {
        return organizationClient.getAllOrganizations(restaurantUserId, pageable);
    }


    @DeleteMapping
    @Operation(summary = "Deletes the given restaurant")
    public ResponseEntity<Void> deleteRestaurant(@RequestParam(value = "restaurantId") UUID restaurantId) {
        return organizationClient.deleteRestaurant(restaurantId);
    }

    @GetMapping("/link-user-to-given-restaurant")
    @Operation(summary = "Given user is linked to given restaurant")
    public ResponseEntity<Void> linkUserToGivenRestaurant(@RequestParam(value = "restaurantId") UUID restaurantId, @RequestParam(value = "userId") UUID userId) {
        return organizationClient.linkUserToGivenRestaurant(restaurantId, userId);
    }

    @DeleteMapping("/unlink-user-from-given-restaurant")
    @Operation(summary = "Given user is removed from given restaurant")
    public ResponseEntity<Void> unlinkGivenUserFromGivenRestaurant(@RequestParam(value = "userId") UUID userId, @RequestParam(value = "restaurantId") UUID restaurantId) {
        return organizationClient.unlinkGivenUserFromGivenRestaurant(userId, restaurantId);
    }

    @PutMapping
    @Operation(summary = "Updates given restaurant")
    public ResponseEntity<UpdateOrganizationDTO> updateRestaurant(@RequestParam(value = "restaurantId") UUID restaurantId, @RequestBody UpdateOrganizationDTO organizationDTO) {
        return organizationClient.updateRestaurant(restaurantId, organizationDTO);
    }
}
