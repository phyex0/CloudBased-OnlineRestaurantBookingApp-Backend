package com.upspoon.organization.controller;


import com.upspoon.common.dto.Organization.NewRestaurantUserDTO;
import com.upspoon.common.dto.Organization.UpdateRestaurantUserDTO;
import com.upspoon.common.web.CustomPage;
import com.upspoon.organization.service.RestaurantUserService;
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
    public ResponseEntity<NewRestaurantUserDTO> addNewUserToRestaurant(@RequestParam(value = "restaurantId") UUID restaurantId, @RequestBody NewRestaurantUserDTO newRestaurantUserDTO) {
        return restaurantUserService.addNewUserToRestaurant(restaurantId, newRestaurantUserDTO);
    }

    @GetMapping
    public ResponseEntity<CustomPage<NewRestaurantUserDTO>> getAllUsers(@RequestParam(value = "organizationId") UUID organizationId, Pageable pageable) {
        return restaurantUserService.getAllUsers(organizationId, pageable);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser(@RequestParam(value = "userId") UUID userId) {
        return restaurantUserService.deleteUser(userId);
    }

    @PutMapping
    public ResponseEntity<UpdateRestaurantUserDTO> updateUser(@RequestParam(value = "userId") UUID userId, @RequestBody UpdateRestaurantUserDTO restaurantUserDTO) {
        return restaurantUserService.updateUser(userId, restaurantUserDTO);
    }

}
