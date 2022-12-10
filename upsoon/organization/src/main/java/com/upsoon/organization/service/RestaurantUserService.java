package com.upsoon.organization.service;

import com.upsoon.common.dto.Organization.NewRestaurantUserDTO;
import com.upsoon.common.dto.Organization.UpdateRestaurantUserDTO;
import com.upsoon.common.web.CustomPage;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

/**
 * @author burak.yesildal
 */

public interface RestaurantUserService {

    ResponseEntity<NewRestaurantUserDTO> addNewUserToRestaurant(UUID restaurantId, NewRestaurantUserDTO newRestaurantUserDTO);

    ResponseEntity<CustomPage<NewRestaurantUserDTO>> getAllUsers(UUID organizationId, Pageable pageable);

    ResponseEntity<Void> deleteUser(UUID userId);

    ResponseEntity<UpdateRestaurantUserDTO> updateUser(UUID userId, UpdateRestaurantUserDTO restaurantUserDTO);

}
