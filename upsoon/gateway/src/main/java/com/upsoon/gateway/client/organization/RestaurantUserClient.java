package com.upsoon.gateway.client.organization;

import com.upsoon.common.dto.Organization.NewRestaurantUserDTO;
import com.upsoon.common.dto.Organization.UpdateRestaurantUserDTO;
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
public interface RestaurantUserClient {

    @PostMapping
    @Operation(summary = "Creates new user and links to given restaurant")
    ResponseEntity<NewRestaurantUserDTO> addNewUserToRestaurant(@RequestParam(value = "restaurantId") UUID restaurantId, @RequestBody NewRestaurantUserDTO newRestaurantUserDTO);

    @GetMapping
    @Operation(summary = "Returns all of the restaurant user related to given organization")
    ResponseEntity<Page<NewRestaurantUserDTO>> getAllUsers(@RequestParam(value = "organizationId") UUID organizationId, Pageable pageable);

    @DeleteMapping
    @Operation(summary = "Deletes the given user")
    ResponseEntity<Void> deleteUser(@RequestParam(value = "userId") UUID userId);

    @PutMapping
    @Operation(summary = "Updates the given user")
    ResponseEntity<UpdateRestaurantUserDTO> updateUser(@RequestParam(value = "userId") UUID userId, @RequestBody UpdateRestaurantUserDTO restaurantUserDTO);
}
