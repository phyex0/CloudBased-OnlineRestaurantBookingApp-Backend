package com.upspoon.organization.service;

import com.upspoon.common.dto.Organization.NewOrganizationCreateDTO;
import com.upspoon.common.dto.Organization.NewOrganizationDTO;
import com.upspoon.common.dto.Organization.UpdateOrganizationDTO;
import com.upspoon.common.web.CustomPage;
import com.upspoon.organization.mapper.NewOrganizationCreateMapper;
import com.upspoon.organization.mapper.NewRestaurantUserCreateMapper;
import com.upspoon.organization.mapper.OrganizationMapper;
import com.upspoon.organization.mapper.RestaurantKafkaEventMapper;
import com.upspoon.organization.model.Organization;
import com.upspoon.organization.model.RestaurantUser;
import com.upspoon.organization.producer.KafkaProducer;
import com.upspoon.organization.repository.OrganizationRepository;
import com.upspoon.organization.repository.RestaurantUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

/**
 * @author burak.yesildal
 */
@Service
@Slf4j
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final RestaurantUserRepository restaurantUserRepository;
    private final OrganizationMapper organizationMapper;
    private final NewRestaurantUserCreateMapper newRestaurantUserCreateMapper;
    private final NewOrganizationCreateMapper newOrganizationCreateMapper;
    private final RestaurantKafkaEventMapper restaurantKafkaEventMapper;
    @Autowired
    private KafkaProducer kafkaProducer;


    public OrganizationServiceImpl(OrganizationRepository organizationRepository, RestaurantUserRepository restaurantUserRepository, OrganizationMapper organizationMapper, NewRestaurantUserCreateMapper newRestaurantUserCreateMapper, NewOrganizationCreateMapper newOrganizationCreateMapper, RestaurantKafkaEventMapper restaurantKafkaEventMapper) {
        this.organizationRepository = organizationRepository;
        this.restaurantUserRepository = restaurantUserRepository;
        this.organizationMapper = organizationMapper;
        this.newRestaurantUserCreateMapper = newRestaurantUserCreateMapper;
        this.newOrganizationCreateMapper = newOrganizationCreateMapper;
        this.restaurantKafkaEventMapper = restaurantKafkaEventMapper;
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

    //TODO: createBooking goes here!
    //TODO: if u seperated the restaurant and market be careful about the user have that option :D
    //TODO: update restaurant caselerde feign ile rest at :D?

    @Override
    @Transactional
    public ResponseEntity<NewOrganizationDTO> createRestaurant(UUID organizationId, NewOrganizationDTO newOrganizationDTO) {

        var organization = organizationRepository.findById(organizationId);

        if (!organization.isPresent() ||
                !(newOrganizationDTO.isRestaurant() || newOrganizationDTO.isBooking() || newOrganizationDTO.isMarket()))
            return new ResponseEntity<>(newOrganizationDTO, HttpStatus.NOT_FOUND);

        var restaurant = newOrganizationCreateMapper.toEntity(newOrganizationDTO);
        restaurant.setRestaurant(organization.get().isRestaurant());
        restaurant.setBooking(organization.get().isBooking());
        restaurant.setMarket(organization.get().isMarket());


        organization.get().getRestaurantUsers().forEach(user -> {
            restaurant.getRestaurantUsers().add(user);
        });

        restaurant.setParentOrganization(organization.get());
        organizationRepository.save(restaurant);

        //TODO: Kafka event goes here.
        //TODO: logical error: market ve restaurant direkt açıyor. Adam belki birisini açmak ister? servisleri böl. ya da bunu update et
        var organizationToOrder = restaurantKafkaEventMapper.toDto(restaurant);
        kafkaProducer.produce(organizationToOrder);
        //TODO: producer catchde delete yapıyor. Ama araya girme söz konusu olduğu için işlemi tamamlar mı?

        return new ResponseEntity<>(newOrganizationDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CustomPage<NewOrganizationDTO>> getAllOrganizations(UUID restaurantUserId, Pageable pageable) {

        Page<NewOrganizationDTO> organizations = restaurantUserRepository.getAllOrganizations(restaurantUserId, pageable);

        if (organizations.isEmpty())
            return new ResponseEntity<>(new CustomPage<>(new ArrayList<>(), pageable, 0), HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(new CustomPage<>(organizations.getContent(), pageable, organizations.getTotalElements()), HttpStatus.OK);

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
