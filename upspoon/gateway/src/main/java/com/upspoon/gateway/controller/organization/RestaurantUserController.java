package com.upspoon.gateway.controller.organization;

import com.upspoon.common.dto.Organization.NewRestaurantUserDTO;
import com.upspoon.common.dto.Organization.UpdateRestaurantUserDTO;
import com.upspoon.common.web.CustomPage;
import com.upspoon.gateway.client.organization.RestaurantUserClient;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author burak.yesildal
 */

@RestController
@RequestMapping("/restaurant-user/api")
@Api("Restaurant User Controller")
public class RestaurantUserController {

    private final RestaurantUserClient restaurantUserClient;

    public RestaurantUserController(RestaurantUserClient restaurantUserClient) {
        this.restaurantUserClient = restaurantUserClient;
    }


    @PostMapping
    @Operation(summary = "Creates new user and links to given restaurant")
    public ResponseEntity<NewRestaurantUserDTO> addNewUserToRestaurant(@RequestParam(value = "restaurantId") UUID restaurantId, @RequestBody NewRestaurantUserDTO newRestaurantUserDTO) {
        return restaurantUserClient.addNewUserToRestaurant(restaurantId, newRestaurantUserDTO);
    }

    @GetMapping
    @Operation(summary = "Returns all of the restaurant user related to given organization")
    public ResponseEntity<CustomPage<NewRestaurantUserDTO>> getAllUsers(@RequestParam(value = "organizationId") UUID organizationId, Pageable pageable) {
        return restaurantUserClient.getAllUsers(organizationId, pageable);
    }

    @DeleteMapping
    @Operation(summary = "Deletes the given user")
    public ResponseEntity<Void> deleteUser(@RequestParam(value = "userId") UUID userId) {
        return restaurantUserClient.deleteUser(userId);
    }

    @PutMapping
    @Operation(summary = "Updates the given user")
    public ResponseEntity<UpdateRestaurantUserDTO> updateUser(@RequestParam(value = "userId") UUID userId, @RequestBody UpdateRestaurantUserDTO restaurantUserDTO) {
        return restaurantUserClient.updateUser(userId, restaurantUserDTO);
    }

}
