package com.upsoon.organization.service;

import com.upsoon.common.dto.Organization.NewRestaurantUserDTO;
import com.upsoon.common.dto.Organization.UpdateRestaurantUserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;


import java.util.UUID;

/**
 * @author Halit Burak Yeşildal
 */

public interface RestaurantUserService {

    ResponseEntity<NewRestaurantUserDTO> addNewUserToRestaurant(UUID restaurantId, NewRestaurantUserDTO newRestaurantUserDTO);

    ResponseEntity<Page<NewRestaurantUserDTO>> getAllUsers(UUID organizationId, Pageable pageable);

    ResponseEntity<Void> deleteUser(UUID userId);

    ResponseEntity<UpdateRestaurantUserDTO> updateUser(UUID userId, UpdateRestaurantUserDTO restaurantUserDTO);

}
