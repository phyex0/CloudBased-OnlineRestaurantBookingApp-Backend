package com.upsoon.order.service;

import com.upsoon.common.dto.Order.*;
import com.upsoon.common.enums.BusinessTypes;
import com.upsoon.common.kafkaTemplateDTO.OrganizationToOrder;
import com.upsoon.order.mapper.MenuMapper;
import com.upsoon.order.mapper.OrganizationFromOrganizationServiceMapper;
import com.upsoon.order.mapper.OrganizationMapper;
import com.upsoon.order.mapper.ProductMapper;
import com.upsoon.order.model.Organization;
import com.upsoon.order.producer.KafkaProducer;
import com.upsoon.order.repository.BusinessRepository;
import com.upsoon.order.repository.MenuRepository;
import com.upsoon.order.repository.OrganizationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.awt.SystemColor.menu;

/**
 * @author Halit Burak Yeşildal
 */


@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrganizationFromOrganizationServiceMapper organizationFromOrganizationServiceMapper;
    private final OrganizationRepository organizationRepository;
    private final KafkaProducer kafkaProducer;
    private final MenuMapper menuMapper;
    private final MenuRepository menuRepository;
    private final OrganizationMapper organizationMapper;
    private final ProductMapper productMapper;
    private final BusinessRepository businessRepository;

    public OrderServiceImpl(OrganizationFromOrganizationServiceMapper organizationFromOrganizationServiceMapper, OrganizationRepository organizationRepository, KafkaProducer kafkaProducer, MenuMapper menuMapper, MenuRepository menuRepository, OrganizationMapper organizationMapper, ProductMapper productMapper, BusinessRepository businessRepository) {
        this.organizationFromOrganizationServiceMapper = organizationFromOrganizationServiceMapper;
        this.organizationRepository = organizationRepository;
        this.kafkaProducer = kafkaProducer;
        this.menuMapper = menuMapper;
        this.menuRepository = menuRepository;
        this.organizationMapper = organizationMapper;
        this.productMapper = productMapper;
        this.businessRepository = businessRepository;
    }

    @Override
    @Transactional
    public void saveOrganization(OrganizationToOrder organizationToOrder) {
        var organization = organizationFromOrganizationServiceMapper.toEntity(organizationToOrder);
        organizationRepository.save(organization);
    }


    @Override
    public ResponseEntity<MenuDTO> createMenu(UUID organizationId, BusinessTypes businessTypes, MenuDTO menuDTO) {
        var organization = organizationRepository.findOrganizationByExactOrganizationId(organizationId);

        if (Objects.isNull(organization)) {
            return new ResponseEntity<MenuDTO>(menuDTO, HttpStatus.NOT_FOUND);
        }

        var menu = menuMapper.toEntity(menuDTO);
        organization.getBusiness(businessTypes).getMenuList().add(menu);
        organizationRepository.save(organization);


        return new ResponseEntity<>(menuDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UpdateMenuDTO> updateMenu(UUID organizationId, UUID menuId, BusinessTypes businessTypes, UpdateMenuDTO menuDTO) {

        var organization = organizationRepository.findOrganizationByExactOrganizationId(organizationId);

        if (Objects.isNull(organization)) {
            return new ResponseEntity<>(menuDTO, HttpStatus.NOT_FOUND);
        }

        //TODO: be sure the following map works well.
        organization.getBusiness(businessTypes).getMenuList().forEach(menu -> {
            if (menu.getId().equals(menuId)) {
                menu = menuMapper.updateEntity(menu, menuDTO);
            }
        });

        organizationRepository.save(organization);

        return new ResponseEntity<>(menuDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteMenu(UUID organizationId, UUID menuId) {
        var organization = organizationRepository.findOrganizationByExactOrganizationId(organizationId);

        if (Objects.isNull(organization)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (organization.getMarket() != null)
            organization.getMarket().getMenuList().removeIf(menu -> menu.getId().equals(menuId));
        if (organization.getRestaurant() != null)
            organization.getRestaurant().getMenuList().removeIf(menu -> menu.getId().equals(menuId));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Page<MenuDTO>> getMenu(UUID organizationId, BusinessTypes businessTypes, Pageable pageable) {

        var organization = organizationRepository.findOrganizationByExactOrganizationId(organizationId);

        if (Objects.isNull(organization)) {
            return new ResponseEntity<>(Page.empty(), HttpStatus.NOT_FOUND);
        }

        var menuIdList = organization.getBusiness(businessTypes).getMenuList().stream().map(menu -> menu.getId()).collect(Collectors.toList());
        var menuList = menuRepository.getMenuByIdList(menuIdList, pageable);

        return new ResponseEntity<Page<MenuDTO>>(menuList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<OrganizationDTO> getOrganization(UUID organizationId) {

        var organization = organizationRepository.findOrganizationByExactOrganizationId(organizationId);

        if (Objects.isNull(organization)) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        var dto = organizationMapper.toDto(organization);

        return new ResponseEntity<>(dto, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<Page<BusinessDTOForUI>> getAllOrganizations(BusinessTypes businessTypes, Pageable pageable) {
        var organizations = businessRepository.getAllOrganizationByBusinessType(businessTypes, pageable);
        return new ResponseEntity<>(organizations, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductDTO> createProduct(UUID organizationId, UUID menuId, ProductDTO productDTO, BusinessTypes businessTypes) {

        var organization = organizationRepository.findOrganizationByExactOrganizationId(organizationId);

        if (Objects.isNull(organization))
            return new ResponseEntity<>(productDTO, HttpStatus.NOT_FOUND);
        if (organization.getBusiness(businessTypes).getMenuList().stream().filter(menuItem -> menuItem.getId().equals(menuId)).findFirst().isEmpty())
            return new ResponseEntity<>(productDTO, HttpStatus.NOT_FOUND);

        var product = productMapper.toEntity(productDTO);

        organization.getBusiness(businessTypes).getMenuList()
                .stream().filter(menu -> menu.getId().equals(menuId))
                .findFirst().get().getProductList().add(product);

        organizationRepository.save(organization);

        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UpdateProductDTO> updateProduct(UUID organizationId, UUID menuId, UUID productId, UpdateProductDTO updateProductDTO, BusinessTypes businessTypes) {

        var organization = organizationRepository.findOrganizationByExactOrganizationId(organizationId);

        if (Objects.isNull(organization))
            return new ResponseEntity<>(updateProductDTO, HttpStatus.NOT_FOUND);
        if (organization.getBusiness(businessTypes).getMenuList().stream().filter(menu -> menu.getId().equals(menuId)).findFirst().isEmpty())
            return new ResponseEntity<>(updateProductDTO, HttpStatus.NOT_FOUND);


        return null;
    }

    @Override
    public ResponseEntity<Void> deleteProduct(UUID organizationId, UUID productId, UUID menuId, BusinessTypes businessTypes) {

        var organization = organizationRepository.findOrganizationByExactOrganizationId(organizationId);

        if (Objects.isNull(organization))
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        if (organization.getBusiness(businessTypes).getMenuList().stream().filter(menu -> menu.getId().equals(menuId)).findFirst().isEmpty())
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        if (organization.getBusiness(businessTypes).getMenuList().stream()
                .filter(menu -> menu.getId().equals(menuId))
                .findFirst().get().getProductList().stream().filter(prod -> prod.getId().equals(productId)).findFirst().isEmpty())
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);


        organization.getBusiness(businessTypes).getMenuList().forEach(menu -> {
            menu.getProductList().removeIf(prod -> prod.getId().equals(productId));
        });
        organizationRepository.save(organization);


        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductDTO> getProduct(UUID organizationId, UUID productId) {
        var organization = organizationRepository.findOrganizationByExactOrganizationId(organizationId);

        if (Objects.isNull(organization))
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        ProductDTO dto = extractDtoFromOrganization(organization, BusinessTypes.RESTAURANT, productId);
        if (!Objects.isNull(dto))
            return new ResponseEntity<>(dto, HttpStatus.OK);
        dto = extractDtoFromOrganization(organization, BusinessTypes.MARKET, productId);
        if (!Objects.isNull(dto))
            return new ResponseEntity<>(dto, HttpStatus.OK);

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

    }

    private ProductDTO extractDtoFromOrganization(Organization organization, BusinessTypes businessTypes, UUID productId) {
        var business = organization.getBusiness(businessTypes);
        if (!Objects.isNull(business)) {
            var selectedProduct = business.getMenuList().stream().flatMap(menu -> menu.getProductList().stream()).filter(prod -> prod.getId().equals(productId)).findFirst();
            if (selectedProduct.isPresent()) {
                var dto = productMapper.toDto(selectedProduct.get());
                return dto;
            }
        }

        return null;


    }


    @Override
    public ResponseEntity<Page<ProductDTO>> getProducts(UUID organizationId, BusinessTypes businessTypes) {
        return null;
    }
}
