package com.upspoon.gateway.controller.organization;


import com.upspoon.common.dto.Organization.NewOrganizationCreateDTO;
import com.upspoon.common.dto.Organization.NewOrganizationDTO;
import com.upspoon.common.dto.Organization.UpdateOrganizationDTO;
import com.upspoon.common.web.CustomPage;
import com.upspoon.gateway.client.organization.OrganizationClient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author burak.yesildal
 */

@RestController
@RequestMapping("/organization/api")
@Tag(name = "Organization Controller")
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

    @PostMapping("/create-business")
    @PreAuthorize("hasPermission('ROLE','ADMIN_ROLE')")
    @Operation(summary = "Creates a new organization and links to given organization")
    public ResponseEntity<NewOrganizationDTO> createRestaurant(@RequestParam(value = "organizationId") UUID organizationId, @RequestBody NewOrganizationDTO newOrganizationDTO) {
        return organizationClient.createBusiness(organizationId, newOrganizationDTO);
    }


    @GetMapping
    @PreAuthorize("hasPermission('ROLE','ADMIN_ROLE')")
    @Operation(summary = "Returns all of the organizations and restaurant for related restaurantUser")
    public ResponseEntity<CustomPage<NewOrganizationDTO>> getAllOrganizations(@RequestParam(value = "restaurantUserId") UUID restaurantUserId, Pageable pageable) {
        return organizationClient.getAllOrganizations(restaurantUserId, pageable);
    }


    @DeleteMapping
    @PreAuthorize("hasPermission('ROLE','ADMIN_ROLE')")
    @Operation(summary = "Deletes the given restaurant")
    public ResponseEntity<Void> deleteRestaurant(@RequestParam(value = "restaurantId") UUID restaurantId) {
        return organizationClient.deleteRestaurant(restaurantId);
    }

    @GetMapping("/link-user-to-given-restaurant")
    @PreAuthorize("hasPermission('ROLE','ADMIN_ROLE')")
    @Operation(summary = "Given user is linked to given restaurant")
    public ResponseEntity<Void> linkUserToGivenRestaurant(@RequestParam(value = "restaurantId") UUID restaurantId, @RequestParam(value = "userId") UUID userId) {
        return organizationClient.linkUserToGivenRestaurant(restaurantId, userId);
    }

    @DeleteMapping("/unlink-user-from-given-restaurant")
    @PreAuthorize("hasPermission('ROLE','ADMIN_ROLE')")
    @Operation(summary = "Given user is removed from given restaurant")
    public ResponseEntity<Void> unlinkGivenUserFromGivenRestaurant(@RequestParam(value = "userId") UUID userId, @RequestParam(value = "restaurantId") UUID restaurantId) {
        return organizationClient.unlinkGivenUserFromGivenRestaurant(userId, restaurantId);
    }

    @PutMapping
    @PreAuthorize("hasPermission('ROLE', 'ORGANIZATION_ROLE') or hasPermission('ROLE','ADMIN_ROLE')")
    @Operation(summary = "Updates given restaurant")
    public ResponseEntity<UpdateOrganizationDTO> updateRestaurant(@RequestParam(value = "restaurantId") UUID restaurantId, @RequestBody UpdateOrganizationDTO organizationDTO) {
        return organizationClient.updateRestaurant(restaurantId, organizationDTO);
    }
}
