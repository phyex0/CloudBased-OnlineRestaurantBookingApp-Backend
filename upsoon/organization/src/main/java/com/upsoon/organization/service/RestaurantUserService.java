package com.upsoon.organization.service;

import com.upsoon.common.dto.NewRestaurantUserDTO;
import org.springframework.http.ResponseEntity;


import java.util.UUID;

public interface RestaurantUserService {

    ResponseEntity<NewRestaurantUserDTO> addNewUserToRestaurant(UUID restaurantId, NewRestaurantUserDTO newRestaurantUserDTO);
}
