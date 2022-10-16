package com.upsoon.organization.service;

import com.upsoon.common.dto.NewOrganizationCreateDTO;
import com.upsoon.common.dto.NewOrganizationDTO;
import com.upsoon.common.dto.UpdateOrganizationDTO;
import com.upsoon.common.enums.PackageService;
import com.upsoon.organization.mapper.NewOrganizationCreateMapper;
import com.upsoon.organization.mapper.NewRestaurantUserCreateMapper;
import com.upsoon.organization.mapper.OrganizationMapper;
import com.upsoon.organization.model.Organization;
import com.upsoon.organization.model.RestaurantUser;
import com.upsoon.organization.repository.OrganizationRepository;
import com.upsoon.organization.repository.RestaurantUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
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
    @Transactional
    public ResponseEntity<NewOrganizationDTO> createRestaurant(UUID organizationId, NewOrganizationDTO newOrganizationDTO) {

        var organization = organizationRepository.findById(organizationId);

        if (!organization.isPresent())
            return new ResponseEntity<>(newOrganizationDTO, HttpStatus.NOT_FOUND);

        var restaurant = newOrganizationCreateMapper.toEntity(newOrganizationDTO);

        organization.get().getRestaurantUsers().forEach(user -> {
            restaurant.getRestaurantUsers().add(user);
        });

        restaurant.setParentOrganization(organization.get());
        organizationRepository.save(restaurant);

        //TODO: Kafka event goes here.


        return new ResponseEntity<>(newOrganizationDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Page<NewOrganizationDTO>> getAllOrganizations(UUID restaurantUserId, Pageable pageable) {

        Page<NewOrganizationDTO> organizations = restaurantUserRepository.getAllOrganizations(restaurantUserId, pageable);

        if (organizations.isEmpty())
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(organizations, HttpStatus.OK);

    }

    @Override
    @Transactional
    public ResponseEntity<Void> deleteRestaurant(UUID restaurantId) {

        var restaurant = organizationRepository.findById(restaurantId);

        if (Objects.isNull(restaurant))
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        var restaurantObject = restaurant.get();

        restaurantObject.getRestaurantUsers().clear();
        restaurantObject.setParentOrganization(null);
        restaurantObject.setDeleted(Boolean.TRUE);
        organizationRepository.save(restaurantObject);


        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<Void> linkUserToGivenRestaurant(UUID restaurantId, UUID userId) {

        var user = restaurantUserRepository.findById(userId);
        var restaurant = organizationRepository.findById(restaurantId);

        if (!user.isPresent() || !restaurant.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        user.get().getOrganizations().add(restaurant.get());
        restaurant.get().getRestaurantUsers().add(user.get());

        restaurantUserRepository.save(user.get());
        organizationRepository.save(restaurant.get());

        return new ResponseEntity<>(HttpStatus.OK);


    }


    @Override
    @Transactional
    public ResponseEntity<Void> unlinkGivenUserFromGivenRestaurant(UUID userId, UUID restaurantId) {

        var user = restaurantUserRepository.findById(userId);
        var restaurant = organizationRepository.findById(restaurantId);

        if (!user.isPresent() || !restaurant.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        user.get().getOrganizations().remove(restaurant.get());
        restaurant.get().getRestaurantUsers().remove(user.get());

        restaurantUserRepository.save(user.get());
        organizationRepository.save(restaurant.get());

        return new ResponseEntity<>(HttpStatus.OK);


    }

    @Override
    @Transactional
    public ResponseEntity<UpdateOrganizationDTO> updateRestaurant(UUID restaurantId, UpdateOrganizationDTO organizationDTO) {

        var organization = organizationRepository.findById(restaurantId);

        if (!organization.isPresent())
            return new ResponseEntity<>(organizationDTO, HttpStatus.NOT_FOUND);

        var updatedOrganization = newOrganizationCreateMapper.updateEntity(organization.get(), organizationDTO);
        organizationRepository.save(updatedOrganization);

        return new ResponseEntity<>(organizationDTO, HttpStatus.OK);

    }
}
