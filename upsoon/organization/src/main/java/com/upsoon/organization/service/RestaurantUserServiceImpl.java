package com.upsoon.organization.service;


import com.upsoon.common.dto.NewRestaurantUserDTO;
import com.upsoon.organization.mapper.NewRestaurantUserCreateMapper;
import com.upsoon.organization.repository.OrganizationRepository;
import com.upsoon.organization.repository.RestaurantUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
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
    public ResponseEntity<NewRestaurantUserDTO> addNewUserToRestaurant(UUID restaurantId, NewRestaurantUserDTO newRestaurantUserDTO) {

        var restaurant = organizationRepository.findById(restaurantId);

        if (!restaurant.isPresent())
            return new ResponseEntity<>(newRestaurantUserDTO, HttpStatus.NOT_FOUND);


        var restaurantUser = newRestaurantUserCreateMapper.toEntity(newRestaurantUserDTO);
        restaurant.get().getRestaurantUsers().add(restaurantUser);
        organizationRepository.save(restaurant.get());


        return new ResponseEntity<>(newRestaurantUserDTO, HttpStatus.OK);
    }
}
