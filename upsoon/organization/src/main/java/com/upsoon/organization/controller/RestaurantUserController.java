package com.upsoon.organization.controller;


import com.upsoon.common.dto.NewRestaurantUserDTO;
import com.upsoon.organization.service.RestaurantUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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


}
