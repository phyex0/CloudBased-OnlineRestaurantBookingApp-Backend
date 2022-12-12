package com.upspoon.order.service;

import com.upspoon.common.dto.Order.*;
import com.upspoon.common.dto.Stock.CreateStockDTO;
import com.upspoon.common.enums.BusinessTypes;
import com.upspoon.common.enums.OrderStatus;
import com.upspoon.common.kafkaTemplateDTO.OrderToStock;
import com.upspoon.common.kafkaTemplateDTO.OrganizationToOrder;
import com.upspoon.common.kafkaTemplateDTO.StockToPayment;
import com.upspoon.common.web.CustomPage;
import com.upspoon.order.client.StockClient;
import com.upspoon.order.mapper.*;
import com.upspoon.order.model.Organization;
import com.upspoon.order.producer.KafkaProducer;
import com.upspoon.order.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author burak.yesildal
 */


@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrganizationFromOrganizationServiceMapper organizationFromOrganizationServiceMapper;
    private final OrganizationRepository organizationRepository;
    private final MenuMapper menuMapper;
    private final MenuRepository menuRepository;
    private final OrganizationMapper organizationMapper;
    private final ProductMapper productMapper;
    private final BusinessRepository businessRepository;
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final OrderToStockMapper orderToStockMapper;
    private final StockClient stockClient;
    private final OrderHistoryMapper orderHistoryMapper;

    @Autowired
    private KafkaProducer kafkaProducer;

    public OrderServiceImpl(OrganizationFromOrganizationServiceMapper organizationFromOrganizationServiceMapper, OrganizationRepository organizationRepository, MenuMapper menuMapper, MenuRepository menuRepository, OrganizationMapper organizationMapper, ProductMapper productMapper, BusinessRepository businessRepository, ProductRepository productRepository, OrderMapper orderMapper, OrderRepository orderRepository, OrderToStockMapper orderToStockMapper, StockClient stockClient, OrderHistoryMapper orderHistoryMapper) {
        this.organizationFromOrganizationServiceMapper = organizationFromOrganizationServiceMapper;
        this.organizationRepository = organizationRepository;
        this.menuMapper = menuMapper;
        this.menuRepository = menuRepository;
        this.organizationMapper = organizationMapper;
        this.productMapper = productMapper;
        this.businessRepository = businessRepository;
        this.productRepository = productRepository;
        this.orderMapper = orderMapper;
        this.orderRepository = orderRepository;
        this.orderToStockMapper = orderToStockMapper;
        this.stockClient = stockClient;
        this.orderHistoryMapper = orderHistoryMapper;
    }

    @Override
    @Transactional
    public void saveOrganization(OrganizationToOrder organizationToOrder) {
        var organization = organizationFromOrganizationServiceMapper.toEntity(organizationToOrder);
        organizationRepository.save(organization);
    }

    @Override
    @Transactional
    public void rollbackOrder(OrderToStock orderToStock) {
        var order = orderRepository.findById(orderToStock.getOrderId());

        if (order.isEmpty())
            log.info("wtf?");

        order.get().setOrderStatus(orderToStock.getOrderStatus());
        orderRepository.save(order.get());

    }

    @Override
    @Transactional
    public ResponseEntity<MenuDTO> createMenu(UUID organizationId, BusinessTypes businessTypes, MenuDTO menuDTO) {
        var organization = organizationRepository.findOrganizationByExactOrganizationId(organizationId);

        if (Objects.isNull(organization)) {
            return new ResponseEntity<MenuDTO>(menuDTO, HttpStatus.NOT_FOUND);
        }
        //TODO if selected menu is null handle it.
        var menu = menuMapper.toEntity(menuDTO);
        organization.getBusiness(businessTypes).getMenuList().add(menu);
        organizationRepository.save(organization);


        return new ResponseEntity<>(menuDTO, HttpStatus.OK);
    }

    @Override
    @Transactional
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
    @Transactional
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
    public ResponseEntity<CustomPage<MenuDTO>> getMenu(UUID organizationId, BusinessTypes businessTypes, Pageable pageable) {

        var organization = organizationRepository.findOrganizationByExactOrganizationId(organizationId);

        if (Objects.isNull(organization)) {
            return new ResponseEntity<>(new CustomPage<>(new ArrayList<>(), pageable, 0), HttpStatus.NOT_FOUND);
        }
        //TODO obje null ise exception
        var menuIdList = organization.getBusiness(businessTypes).getMenuList().stream().map(menu -> menu.getId()).collect(Collectors.toList());
        var menuList = menuRepository.getMenuByIdList(menuIdList, pageable);

        return new ResponseEntity<CustomPage<MenuDTO>>(new CustomPage<>(menuList.getContent(), pageable, menuList.getTotalElements()), HttpStatus.OK);
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
    public ResponseEntity<CustomPage<BusinessDTOForUI>> getAllOrganizations(BusinessTypes businessTypes, Pageable pageable) {
        var organizations = businessRepository.getAllOrganizationByBusinessType(businessTypes, pageable);
        return new ResponseEntity<>(new CustomPage<>(organizations.getContent(), pageable, organizations.getTotalElements()), HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<ProductDTO> createProduct(UUID organizationId, UUID menuId, ProductDTO productDTO, BusinessTypes businessTypes) {
        //TODO: product code gotto be unique add constraint!
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
        organizationRepository.flush();
        product = productRepository.findProductByProductCode(product.getProductCode());
        stockClient.createStock(new CreateStockDTO(product.getId(), 0L));

        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<UpdateProductDTO> updateProduct(UUID organizationId, UUID menuId, UUID productId, UpdateProductDTO updateProductDTO, BusinessTypes businessTypes) {

        var organization = organizationRepository.findOrganizationByExactOrganizationId(organizationId);

        if (Objects.isNull(organization))
            return new ResponseEntity<>(updateProductDTO, HttpStatus.NOT_FOUND);
        if (organization.getBusiness(businessTypes).getMenuList().stream().filter(menu -> menu.getId().equals(menuId)).findFirst().isEmpty())
            return new ResponseEntity<>(updateProductDTO, HttpStatus.NOT_FOUND);


        return null;
    }

    @Override
    @Transactional
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
    public ResponseEntity<CustomPage<ProductDTO>> getProducts(UUID organizationId, UUID menuID, BusinessTypes businessTypes, Pageable pageable) {

        var organization = organizationRepository.findOrganizationByExactOrganizationId(organizationId);

        if (Objects.isNull(organization))
            return new ResponseEntity<>(null, HttpStatus.OK);
        if (Objects.isNull(organization.getBusiness(businessTypes)))
            return new ResponseEntity<>(null, HttpStatus.OK);

        var businessId = organization.getBusiness(businessTypes).getId();


        var product = productRepository.getAllProducts(businessId, menuID, pageable);

        var productDto = productMapper.toDto(product.getContent());
        Page<ProductDTO> page = new PageImpl<>(productDto);

        return new ResponseEntity<>(new CustomPage<>(page.getContent(), pageable, page.getTotalElements()), HttpStatus.OK);

    }

    @Override
    @Transactional
    public ResponseEntity<OrderDTO> createOrder(OrderDTO orderDTO) {

        var containsAll = productRepository.existsAllByIdIn(orderDTO.getProductId());

        if (!containsAll) {
            return new ResponseEntity<>(orderDTO, HttpStatus.NOT_FOUND);
        }

        var order = orderMapper.toEntity(orderDTO);

        Double totalAmount = productRepository.getTotalAmount(orderDTO.getProductId());
        order.setTotalAmount(totalAmount);

        var orderToStock = orderToStockMapper.toDto(order);
        orderToStock.setOrderStatus(OrderStatus.ORDER_CREATED);

        order = orderRepository.save(order);
        orderToStock.setOrderId(order.getId());
        orderToStock.setPrice(totalAmount);
        kafkaProducer.produceOrderCreatedEvent(orderToStock);


        return null;
    }

    @Override
    public ResponseEntity<CustomPage<OrderHistoryDTO>> orderHistory(UUID userId, Pageable pageable) {
        var orderPage = orderRepository.getAllByUserId(userId, pageable);
        var ordersDTOList = orderHistoryMapper.toDto(orderPage.getContent());

        var productIdList = ordersDTOList.stream().flatMap(order -> order.getProductId().stream()).collect(Collectors.toList());
        var products = productRepository.findAllByIdIn(productIdList);
        Map<UUID, ProductDTO> productMap = new HashMap<>();
        products.forEach(p -> productMap.put(p.getId(), productMapper.toDto(p)));

        ordersDTOList.forEach(order -> {
            List<ProductDTO> productDtoList = new ArrayList<>();
            order.getProductId().forEach(product -> productDtoList.add(productMap.get(product)));
            order.setProductDTOList(productDtoList);
        });


        return new ResponseEntity<>(new CustomPage<>(ordersDTOList, pageable, orderPage.getTotalElements()), HttpStatus.OK);
    }

    @Override
    @Transactional
    public void updateOrderSuccess(StockToPayment stockToPayment) {
        var order = orderRepository.findById(stockToPayment.getOrderId());
        order.get().setOrderStatus(OrderStatus.SUCCESS);
        orderRepository.save(order.get());
    }
}
