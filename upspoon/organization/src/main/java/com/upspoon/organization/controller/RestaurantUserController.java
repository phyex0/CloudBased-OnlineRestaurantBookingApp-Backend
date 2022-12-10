package com.upspoon.organization.controller;


import com.upspoon.common.dto.Organization.NewRestaurantUserDTO;
import com.upspoon.common.dto.Organization.UpdateRestaurantUserDTO;
import com.upspoon.common.web.CustomPage;
import com.upspoon.organization.service.RestaurantUserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author burak.yesildal
 */

@RestController
@RequestMapping("/api/restaurant-user")
public class RestaurantUserController {

    private final RestaurantUserService restaurantUserService;

    public RestaurantUserController(RestaurantUserService restaurantUserService) {
        this.restaurantUserService = restaurantUserService;
    }

    @PostMapping
    @Operation(summary = "Creates new user and links to given restaurant")
    public ResponseEntity<NewRestaurantUserDTO> addNewUserToRestaurant(@RequestParam(value = "restaurantId") UUID restaurantId, @RequestBody NewRestaurantUserDTO newRestaurantUserDTO) {
        return restaurantUserService.addNewUserToRestaurant(restaurantId, newRestaurantUserDTO);
    }

    @GetMapping
    @Operation(summary = "Returns all of the restaurant user related to given organization")
    public ResponseEntity<CustomPage<NewRestaurantUserDTO>> getAllUsers(@RequestParam(value = "organizationId") UUID organizationId, Pageable pageable) {
        return restaurantUserService.getAllUsers(organizationId, pageable);
    }

    @DeleteMapping
    @Operation(summary = "Deletes the given user")
    public ResponseEntity<Void> deleteUser(@RequestParam(value = "userId") UUID userId) {
        return restaurantUserService.deleteUser(userId);
    }

    @PutMapping
    @Operation(summary = "Updates the given user")
    public ResponseEntity<UpdateRestaurantUserDTO> updateUser(@RequestParam(value = "userId") UUID userId, @RequestBody UpdateRestaurantUserDTO restaurantUserDTO) {
        return restaurantUserService.updateUser(userId, restaurantUserDTO);
    }

}
