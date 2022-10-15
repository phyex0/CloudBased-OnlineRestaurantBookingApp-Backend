package com.upsoon.organization.service;

import com.upsoon.common.dto.NewRestaurantUserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;


import java.util.UUID;

public interface RestaurantUserService {

    ResponseEntity<NewRestaurantUserDTO> addNewUserToRestaurant(UUID restaurantId, NewRestaurantUserDTO newRestaurantUserDTO);

    ResponseEntity<Page<NewRestaurantUserDTO>> getAllUsers(UUID organizationId, Pageable pageable);
}
