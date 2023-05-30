package com.upspoon.gateway.controller.organization;

import com.upspoon.common.dto.Organization.NewRestaurantUserDTO;
import com.upspoon.common.dto.Organization.RestaurantUserDTO;
import com.upspoon.common.dto.Organization.UpdateRestaurantUserDTO;
import com.upspoon.common.web.CustomPage;
import com.upspoon.gateway.client.organization.RestaurantUserClient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author burak.yesildal
 */

@RestController
@RequestMapping("/restaurant-user/api")
@Tag(name = "Restaurant User Controller")
public class RestaurantUserController {

    private final RestaurantUserClient restaurantUserClient;

    public RestaurantUserController(RestaurantUserClient restaurantUserClient) {
        this.restaurantUserClient = restaurantUserClient;
    }


    @PostMapping
    @PreAuthorize("hasPermission('ROLE', 'ORGANIZATION_ROLE') or hasPermission('ROLE','ADMIN_ROLE')")
    @Operation(summary = "Creates new user and links to given restaurant")
    public ResponseEntity<NewRestaurantUserDTO> addNewUserToRestaurant(@RequestParam(value = "restaurantId") UUID restaurantId, @RequestBody NewRestaurantUserDTO newRestaurantUserDTO) {
        return restaurantUserClient.addNewUserToRestaurant(restaurantId, newRestaurantUserDTO);
    }

    @GetMapping
    @PreAuthorize("hasPermission('ROLE', 'ORGANIZATION_ROLE') or hasPermission('ROLE','ADMIN_ROLE')")
    @Operation(summary = "Returns all of the restaurant user related to given organization")
    public ResponseEntity<CustomPage<NewRestaurantUserDTO>> getAllUsers(@RequestParam(value = "organizationId") UUID organizationId, Pageable pageable) {
        return restaurantUserClient.getAllUsers(organizationId, pageable);
    }

    @DeleteMapping
    @PreAuthorize("hasPermission('ROLE','ADMIN_ROLE')")
    @Operation(summary = "Deletes the given user")
    public ResponseEntity<Void> deleteUser(@RequestParam(value = "userId") UUID userId) {
        return restaurantUserClient.deleteUser(userId);
    }

    @PutMapping
    @PreAuthorize("hasPermission('ROLE','ADMIN_ROLE')")
    @Operation(summary = "Updates the given user")
    public ResponseEntity<UpdateRestaurantUserDTO> updateUser(@RequestParam(value = "userId") UUID userId, @RequestBody UpdateRestaurantUserDTO restaurantUserDTO) {
        return restaurantUserClient.updateUser(userId, restaurantUserDTO);
    }

    @GetMapping(value = "/find-by-mail")
    @Operation(summary = "Returns user and related organization by mail")
    @PreAuthorize("hasPermission('ROLE', 'ORGANIZATION_ROLE') or hasPermission('ROLE','ADMIN_ROLE') or hasPermission('ROLE', 'BUSINESS_ROLE')")
    public ResponseEntity<RestaurantUserDTO> findUserByMail(@RequestParam String mail) {
        return restaurantUserClient.findUserByMail(mail);
    }

}
