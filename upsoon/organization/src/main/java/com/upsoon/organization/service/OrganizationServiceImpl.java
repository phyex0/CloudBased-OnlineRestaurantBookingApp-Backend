package com.upsoon.organization.service;

import com.upsoon.common.dto.OrganizationDTO;
import com.upsoon.common.enums.PackageService;
import com.upsoon.organization.mapper.OrganizationMapper;
import com.upsoon.organization.model.Organization;
import com.upsoon.organization.model.RestaurantUser;
import com.upsoon.organization.repository.OrganizationRepository;
import com.upsoon.organization.repository.RestaurantUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;

    private final RestaurantUserRepository restaurantUserRepository;
    private final OrganizationMapper organizationMapper;

    public OrganizationServiceImpl(OrganizationRepository organizationRepository, RestaurantUserRepository restaurantUserRepository, OrganizationMapper organizationMapper) {
        this.organizationRepository = organizationRepository;
        this.restaurantUserRepository = restaurantUserRepository;
        this.organizationMapper = organizationMapper;
    }

    @Override
    @Transactional
    public ResponseEntity<OrganizationDTO> createOrganization(OrganizationDTO organizationDTO) {

        Organization organization = new Organization();

        RestaurantUser restaurantUser = new RestaurantUser();
        restaurantUser.setName("ekmek");
        restaurantUser.setLastName("ekmek");
        restaurantUser.setEmail("ekmail");
        restaurantUser.setPhoneNumber("112");

        organization.setOrganizationName("Deneme");
        organization.setPackageService(PackageService.NO_CARRIER);
        organization.getRestaurantUsers().add(restaurantUser);
        restaurantUser.getOrganizations().add(organization);
        organizationRepository.save(organization);
        restaurantUserRepository.save(restaurantUser);


        return new ResponseEntity<OrganizationDTO>(organizationDTO, HttpStatus.OK);

    }
}
