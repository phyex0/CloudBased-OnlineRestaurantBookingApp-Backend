package com.upspoon.organization.service;

import com.upspoon.common.dto.Organization.NewOrganizationCreateDTO;
import com.upspoon.common.dto.Organization.NewOrganizationDTO;
import com.upspoon.common.dto.Organization.UpdateOrganizationDTO;
import com.upspoon.common.enums.BusinessTypes;
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

import java.util.*;

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
    public ResponseEntity<NewOrganizationDTO> createBusiness(UUID organizationId, NewOrganizationDTO newOrganizationDTO) {

        var organization = organizationRepository.findById(organizationId);

        if (!organization.isPresent() || !businessTypeMatcher(newOrganizationDTO.getBusinessTypes(), organization.get().getBusinessType()))
            return new ResponseEntity<>(newOrganizationDTO, HttpStatus.NOT_FOUND);
        var restaurant = newOrganizationCreateMapper.toEntity(newOrganizationDTO);
        organization.get().getRestaurantUsers().forEach(user -> {
            restaurant.getRestaurantUsers().add(user);
        });

        restaurant.setParentOrganization(organization.get());
        organizationRepository.save(restaurant);
        //TODO: orgnaizationToBooking mapper should be used!
        if (newOrganizationDTO.getBusinessTypes().equals(BusinessTypes.BOOK))
            kafkaProducer.produceBook(restaurantKafkaEventMapper.toDto(restaurant));
        else
            kafkaProducer.produce(restaurantKafkaEventMapper.toDto(restaurant));
        //TODO: Kafka event goes here.
        //TODO: producer catchde delete yapıyor. Ama araya girme söz konusu olduğu için işlemi tamamlar mı?

        return new ResponseEntity<>(newOrganizationDTO, HttpStatus.OK);
    }

    //if given business type available for organization business type returns true;
    private boolean businessTypeMatcher(BusinessTypes businessTypes, BusinessTypes organizationBusinessType) {
        Map<BusinessTypes, Set<BusinessTypes>> businesTypeMap = new HashMap<>();
        businesTypeMap.put(BusinessTypes.MARKET, new HashSet<>(Arrays.asList(BusinessTypes.ALL, BusinessTypes.MARKET, BusinessTypes.MARKET_RESTAURANT)));
        businesTypeMap.put(BusinessTypes.RESTAURANT, new HashSet<>(Arrays.asList(BusinessTypes.ALL, BusinessTypes.RESTAURANT, BusinessTypes.MARKET_RESTAURANT, BusinessTypes.RESTAURANT_BOOK)));
        businesTypeMap.put(BusinessTypes.BOOK, new HashSet<>(Arrays.asList(BusinessTypes.ALL, BusinessTypes.BOOK, BusinessTypes.RESTAURANT_BOOK)));
        return businesTypeMap.get(businessTypes).contains(organizationBusinessType);
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
        //TODO: kafka event to delete business on order or booking service as well.
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
