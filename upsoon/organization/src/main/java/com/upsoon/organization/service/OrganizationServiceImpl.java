package com.upsoon.organization.service;

import com.upsoon.common.dto.NewOrganizationCreateDTO;
import com.upsoon.common.dto.NewOrganizationDTO;
import com.upsoon.common.dto.OrganizationDTO;
import com.upsoon.common.enums.PackageService;
import com.upsoon.organization.mapper.NewOrganizationCreateMapper;
import com.upsoon.organization.mapper.NewRestaurantUserCreateMapper;
import com.upsoon.organization.mapper.OrganizationMapper;
import com.upsoon.organization.model.Organization;
import com.upsoon.organization.model.RestaurantUser;
import com.upsoon.organization.repository.OrganizationRepository;
import com.upsoon.organization.repository.RestaurantUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final RestaurantUserRepository restaurantUserRepository;
    private final OrganizationMapper organizationMapper;

    private final NewRestaurantUserCreateMapper newRestaurantUserCreateMapper;

    private final NewOrganizationCreateMapper newOrganizationCreateMapper;

    public OrganizationServiceImpl(OrganizationRepository organizationRepository, RestaurantUserRepository restaurantUserRepository, OrganizationMapper organizationMapper, NewRestaurantUserCreateMapper newRestaurantUserCreateMapper, NewOrganizationCreateMapper newOrganizationCreateMapper) {
        this.organizationRepository = organizationRepository;
        this.restaurantUserRepository = restaurantUserRepository;
        this.organizationMapper = organizationMapper;
        this.newRestaurantUserCreateMapper = newRestaurantUserCreateMapper;
        this.newOrganizationCreateMapper = newOrganizationCreateMapper;
    }

    @PostConstruct
    void init() {
        Organization organization = new Organization();
        organization.setFullAddress("Full Address");
        organization.setOrganizationName("Root");
        organization.setPackageService(PackageService.OWN_CARRIER);

        RestaurantUser restaurantUser = new RestaurantUser();
        restaurantUser.setPhoneNumber("+999");
        restaurantUser.setEmail("test@test.1");
        restaurantUser.setName("Root");
        restaurantUser.setLastName("Root");


        organization.getRestaurantUsers().add(restaurantUser);
        restaurantUser.getOrganizations().add(organization);


        organizationRepository.save(organization);
        restaurantUserRepository.save(restaurantUser);

    }


    @Override
    @Transactional
    public ResponseEntity<NewOrganizationCreateDTO> createOrganization(NewOrganizationCreateDTO newOrganizationCreateDTO) {

        Organization organization = newOrganizationCreateMapper.toEntity(newOrganizationCreateDTO.getNewOrganizationDTO());
        RestaurantUser restaurantUser = newRestaurantUserCreateMapper.toEntity(newOrganizationCreateDTO.getNewRestaurantUserDTO());

        organization.getRestaurantUsers().add(restaurantUser);
        restaurantUser.getOrganizations().add(organization);

        organizationRepository.save(organization);
        restaurantUserRepository.save(restaurantUser);

        return new ResponseEntity<>(newOrganizationCreateDTO, HttpStatus.OK);

    }


    @Override
    public ResponseEntity<NewOrganizationDTO> createRestaurant(UUID organizationId, NewOrganizationDTO newOrganizationDTO) {

        var organization = organizationRepository.findById(organizationId);

        if (!organization.isPresent())
            return new ResponseEntity<>(newOrganizationDTO, HttpStatus.NOT_FOUND);

        var restaurant = newOrganizationCreateMapper.toEntity(newOrganizationDTO);


        restaurant.setParentOrganization(organization.get());
        organizationRepository.save(restaurant);


        return new ResponseEntity<>(newOrganizationDTO, HttpStatus.OK);
    }
}
