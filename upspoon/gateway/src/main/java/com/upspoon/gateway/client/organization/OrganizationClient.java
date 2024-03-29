package com.upspoon.gateway.client.organization;

import com.upspoon.common.config.CustomFeignConfiguration;
import com.upspoon.common.dto.Organization.NewOrganizationCreateDTO;
import com.upspoon.common.dto.Organization.NewOrganizationDTO;
import com.upspoon.common.dto.Organization.UpdateOrganizationDTO;
import com.upspoon.common.web.CustomPage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author burak.yesildal
 */

@FeignClient(name = "${client.organization-api.name}", url = "${client.organization-api.url}", configuration = CustomFeignConfiguration.class)
public interface OrganizationClient {

    @PostMapping(value = "/api/organization", produces = "application/json", consumes = "application/json")
    ResponseEntity<NewOrganizationCreateDTO> createOrganization(@RequestBody NewOrganizationCreateDTO newOrganizationCreateDTO);

    @PostMapping(value = "/api/organization/create-business", produces = "application/json", consumes = "application/json")
    ResponseEntity<NewOrganizationDTO> createBusiness(@RequestParam(value = "organizationId") UUID organizationId, @RequestBody NewOrganizationDTO newOrganizationDTO);


    @GetMapping(value = "/api/organization", produces = "application/json", consumes = "application/json")
    ResponseEntity<CustomPage<NewOrganizationDTO>> getAllOrganizations(@RequestParam(value = "restaurantUserId") UUID restaurantUserId, Pageable pageable);


    @DeleteMapping(value = "/api/organization", produces = "application/json", consumes = "application/json")
    ResponseEntity<Void> deleteRestaurant(@RequestParam(value = "restaurantId") UUID restaurantId);

    @GetMapping(value = "/api/organization/link-user-to-given-restaurant", produces = "application/json", consumes = "application/json")
    ResponseEntity<Void> linkUserToGivenRestaurant(@RequestParam(value = "restaurantId") UUID restaurantId, @RequestParam(value = "userId") UUID userId);

    @DeleteMapping(value = "/api/organization/unlink-user-from-given-restaurant", produces = "application/json", consumes = "application/json")
    ResponseEntity<Void> unlinkGivenUserFromGivenRestaurant(@RequestParam(value = "userId") UUID userId, @RequestParam(value = "restaurantId") UUID restaurantId);

    @PutMapping(value = "/api/organization", produces = "application/json", consumes = "application/json")
    ResponseEntity<UpdateOrganizationDTO> updateRestaurant(@RequestParam(value = "restaurantId") UUID restaurantId, @RequestBody UpdateOrganizationDTO organizationDTO);

}
