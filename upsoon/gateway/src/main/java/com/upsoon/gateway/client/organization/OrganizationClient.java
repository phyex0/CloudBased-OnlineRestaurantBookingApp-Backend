package com.upsoon.gateway.client.organization;

import com.upsoon.common.dto.Organization.NewOrganizationCreateDTO;
import com.upsoon.common.dto.Organization.NewOrganizationDTO;
import com.upsoon.common.dto.Organization.UpdateOrganizationDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author Halit Burak Yeşildal
 */

@FeignClient(name = "${client.organization-api.name}", url = "${client.organization-api.url}" /*,configuration*/)
public interface OrganizationClient {

    @PostMapping(value = "/api/organization")
    @Operation(summary = "Creates new organization by taking OrganizationDTO and RestaurantUserDTO")
    ResponseEntity<NewOrganizationCreateDTO> createOrganization(@RequestBody NewOrganizationCreateDTO newOrganizationCreateDTO);

    @PostMapping(value = "/api/organization/create-restaurant")
    @Operation(summary = "Creates a new organization and links to given organization")
    ResponseEntity<NewOrganizationDTO> createRestaurant(@RequestParam(value = "organizationId") UUID organizationId, @RequestBody NewOrganizationDTO newOrganizationDTO);


    @GetMapping(value = "/api/organization")
    @Operation(summary = "Returns all of the organizations and restaurant for related restaurantUser")
    ResponseEntity<Page<NewOrganizationDTO>> getAllOrganizations(@RequestParam(value = "restaurantUserId") UUID restaurantUserId, Pageable pageable);


    @DeleteMapping(value = "/api/organization")
    @Operation(summary = "Deletes the given restaurant")
    ResponseEntity<Void> deleteRestaurant(@RequestParam(value = "restaurantId") UUID restaurantId);

    @GetMapping(value = "/api/organization/link-user-to-given-restaurant")
    @Operation(summary = "Given user is linked to given restaurant")
    ResponseEntity<Void> linkUserToGivenRestaurant(@RequestParam(value = "restaurantId") UUID restaurantId, @RequestParam(value = "userId") UUID userId);

    @DeleteMapping(value = "/api/organization/unlink-user-from-given-restaurant")
    @Operation(summary = "Given user is removed from given restaurant")
    ResponseEntity<Void> unlinkGivenUserFromGivenRestaurant(@RequestParam(value = "userId") UUID userId, @RequestParam(value = "restaurantId") UUID restaurantId);

    @PutMapping(value = "/api/organization")
    @Operation(summary = "Updates given restaurant")
    ResponseEntity<UpdateOrganizationDTO> updateRestaurant(@RequestParam(value = "restaurantId") UUID restaurantId, @RequestBody UpdateOrganizationDTO organizationDTO);

}
