package com.upspoon.organization.service;

import com.upspoon.common.dto.Organization.NewRestaurantUserDTO;
import com.upspoon.common.dto.Organization.RestaurantUserDTO;
import com.upspoon.common.dto.Organization.UpdateRestaurantUserDTO;
import com.upspoon.common.web.CustomPage;
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

    ResponseEntity<RestaurantUserDTO> findUserByMail(String mail);
}
