package com.upspoon.organization.service;


import com.upspoon.common.dto.Organization.NewRestaurantUserDTO;
import com.upspoon.common.dto.Organization.UpdateRestaurantUserDTO;
import com.upspoon.common.exceptions.UserNotFoundException;
import com.upspoon.common.web.CustomPage;
import com.upspoon.organization.mapper.NewRestaurantUserCreateMapper;
import com.upspoon.organization.repository.OrganizationRepository;
import com.upspoon.organization.repository.RestaurantUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * @author burak.yesildal
 */

@Service
@Slf4j
public class RestaurantUserServiceImpl implements RestaurantUserService {

    private final RestaurantUserRepository restaurantUserRepository;

    private final OrganizationRepository organizationRepository;

    private final NewRestaurantUserCreateMapper newRestaurantUserCreateMapper;

    public RestaurantUserServiceImpl(RestaurantUserRepository restaurantUserRepository, OrganizationRepository organizationRepository, NewRestaurantUserCreateMapper newRestaurantUserCreateMapper) {
        this.restaurantUserRepository = restaurantUserRepository;
        this.organizationRepository = organizationRepository;
        this.newRestaurantUserCreateMapper = newRestaurantUserCreateMapper;
    }

    @Override
    @Transactional
    public ResponseEntity<NewRestaurantUserDTO> addNewUserToRestaurant(UUID restaurantId, NewRestaurantUserDTO newRestaurantUserDTO) {

        var restaurant = organizationRepository.findById(restaurantId);

        if (!restaurant.isPresent())
            return new ResponseEntity<>(newRestaurantUserDTO, HttpStatus.NOT_FOUND);


        var restaurantUser = newRestaurantUserCreateMapper.toEntity(newRestaurantUserDTO);
        restaurant.get().getRestaurantUsers().add(restaurantUser);
        organizationRepository.save(restaurant.get());

        //TODO: if this is a child organization add this user to order service by using rest


        return new ResponseEntity<>(newRestaurantUserDTO, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<CustomPage<NewRestaurantUserDTO>> getAllUsers(UUID organizationId, Pageable pageable) {

        Page<NewRestaurantUserDTO> users = organizationRepository.getAllUsers(organizationId, pageable);

        if (users.isEmpty())
            throw new UserNotFoundException();

        return new ResponseEntity<>(new CustomPage<>(users.getContent(), pageable, users.getTotalElements()), HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<Void> deleteUser(UUID userId) {

        var restaurantUser = restaurantUserRepository.findById(userId);

        if (!restaurantUser.isPresent())
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        var organizations = organizationRepository.getAllOrganizationsByUserId(userId);


        organizations.forEach(organization -> {
            organization.getRestaurantUsers().remove(restaurantUser.get());
            organizationRepository.save(organization);
        });


        restaurantUser.get().getOrganizations().clear();
        restaurantUser.get().setDeleted(Boolean.TRUE);
        restaurantUserRepository.save(restaurantUser.get());

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @Override
    @Transactional
    public ResponseEntity<UpdateRestaurantUserDTO> updateUser(UUID userId, UpdateRestaurantUserDTO restaurantUserDTO) {

        var user = restaurantUserRepository.findById(userId);

        if (!user.isPresent())
            return new ResponseEntity<>(restaurantUserDTO, HttpStatus.NOT_FOUND);

        var updatedUser = newRestaurantUserCreateMapper.updateEntity(user.get(), restaurantUserDTO);
        restaurantUserRepository.save(updatedUser);

        return new ResponseEntity<>(restaurantUserDTO, HttpStatus.OK);

    }
}
