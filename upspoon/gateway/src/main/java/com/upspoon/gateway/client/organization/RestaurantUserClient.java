package com.upspoon.gateway.client.organization;

import com.upspoon.common.config.CustomFeignConfiguration;
import com.upspoon.common.dto.Organization.NewRestaurantUserDTO;
import com.upspoon.common.dto.Organization.UpdateRestaurantUserDTO;
import com.upspoon.common.web.CustomPage;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author burak.yesildal
 */

@FeignClient(name = "${client.organization-api.name}", url = "${client.organization-api.url}", configuration = CustomFeignConfiguration.class)
public interface RestaurantUserClient {

    @PostMapping(value = "/api/restaurant-user", produces = "application/json", consumes = "application/json")
    @Operation(summary = "Creates new user and links to given restaurant")
    ResponseEntity<NewRestaurantUserDTO> addNewUserToRestaurant(@RequestParam(value = "restaurantId") UUID restaurantId, @RequestBody NewRestaurantUserDTO newRestaurantUserDTO);

    @GetMapping(value = "/api/restaurant-user", produces = "application/json", consumes = "application/json")
    @Operation(summary = "Returns all of the restaurant user related to given organization")
    ResponseEntity<CustomPage<NewRestaurantUserDTO>> getAllUsers(@RequestParam(value = "organizationId") UUID organizationId, Pageable pageable);

    @DeleteMapping(value = "/api/restaurant-user", produces = "application/json", consumes = "application/json")
    @Operation(summary = "Deletes the given user")
    ResponseEntity<Void> deleteUser(@RequestParam(value = "userId") UUID userId);

    @PutMapping(value = "/api/restaurant-user", produces = "application/json", consumes = "application/json")
    @Operation(summary = "Updates the given user")
    ResponseEntity<UpdateRestaurantUserDTO> updateUser(@RequestParam(value = "userId") UUID userId, @RequestBody UpdateRestaurantUserDTO restaurantUserDTO);
}
