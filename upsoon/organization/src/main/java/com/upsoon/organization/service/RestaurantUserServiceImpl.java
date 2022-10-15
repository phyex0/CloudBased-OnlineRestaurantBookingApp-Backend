package com.upsoon.organization.service;


import com.upsoon.common.dto.NewOrganizationDTO;
import com.upsoon.common.dto.NewRestaurantUserDTO;
import com.upsoon.organization.mapper.NewRestaurantUserCreateMapper;
import com.upsoon.organization.repository.OrganizationRepository;
import com.upsoon.organization.repository.RestaurantUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

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
    public ResponseEntity<Page<NewRestaurantUserDTO>> getAllUsers(UUID organizationId, Pageable pageable) {

        Page<NewRestaurantUserDTO> users = organizationRepository.getAllUsers(organizationId, pageable);

        if (users.isEmpty())
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
