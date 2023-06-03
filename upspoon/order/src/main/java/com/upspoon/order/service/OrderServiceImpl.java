package com.upspoon.order.service;

import com.upspoon.common.dto.Order.*;
import com.upspoon.common.dto.Stock.CreateStockDTO;
import com.upspoon.common.enums.BusinessTypes;
import com.upspoon.common.enums.OrderStatus;
import com.upspoon.common.exceptions.BusinessNotFoundException;
import com.upspoon.common.exceptions.BusinessTypeDoesNotRecognisedException;
import com.upspoon.common.exceptions.MenuNotFoundException;
import com.upspoon.common.exceptions.MissingProductsException;
import com.upspoon.common.kafkaTemplateDTO.OrderToStock;
import com.upspoon.common.kafkaTemplateDTO.OrganizationToOrder;
import com.upspoon.common.kafkaTemplateDTO.StockToPayment;
import com.upspoon.common.web.CustomPage;
import com.upspoon.order.client.StockClient;
import com.upspoon.order.mapper.*;
import com.upspoon.order.model.Organization;
import com.upspoon.order.producer.KafkaProducer;
import com.upspoon.order.repository.MenuRepository;
import com.upspoon.order.repository.OrderRepository;
import com.upspoon.order.repository.OrganizationRepository;
import com.upspoon.order.repository.ProductRepository;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrganizationFromOrganizationServiceMapper organizationFromOrganizationServiceMapper;
    private final OrganizationRepository organizationRepository;
    private final MenuMapper menuMapper;
    private final MenuRepository menuRepository;
    private final OrganizationMapper organizationMapper;
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final OrderToStockMapper orderToStockMapper;
    private final StockClient stockClient;
    private final OrderHistoryMapper orderHistoryMapper;

    @Autowired
    private KafkaProducer kafkaProducer;


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
    public ResponseEntity<MenuDTO> createMenu(UUID organizationId, MenuDTO menuDTO) {
        var organization = organizationRepository.findOrganizationByExactOrganizationId(organizationId);

        if (Objects.isNull(organization)) {
            return new ResponseEntity<MenuDTO>(menuDTO, HttpStatus.NOT_FOUND);
        }
        //TODO if selected menu is null handle it.
        var menu = menuMapper.toEntity(menuDTO);
        organization.getMenuList().add(menu);
        organizationRepository.save(organization);


        return new ResponseEntity<>(menuDTO, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<UpdateMenuDTO> updateMenu(UUID organizationId, UUID menuId, UpdateMenuDTO menuDTO) {

        var organization = organizationRepository.findOrganizationByExactOrganizationId(organizationId);

        if (Objects.isNull(organization)) {
            return new ResponseEntity<>(menuDTO, HttpStatus.NOT_FOUND);
        }

        //TODO: be sure the following map works well.
        organization.getMenuList().forEach(menu -> {
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
        organization.getMenuList().removeIf(menu -> menu.getId().equals(menuId));
        organizationRepository.save(organization);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<MenuDTO>> getMenu(UUID organizationId) {

        var organization = organizationRepository.findOrganizationByExactOrganizationId(organizationId);

        if (Objects.isNull(organization)) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
        }
        OrganizationDTO dto = organizationMapper.toDto(organization);
        return new ResponseEntity<List<MenuDTO>>(dto.getMenuList(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<OrganizationDTO> getOrganization(UUID organizationId) {

        var organization = organizationRepository.findOrganizationByExactOrganizationId(organizationId);

        if (Objects.isNull(organization)) {
            throw new BusinessNotFoundException("Business not found!");
        }
        var dto = organizationMapper.toDto(organization);

        return new ResponseEntity<>(dto, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<CustomPage<BusinessDTOForUI>> getAllOrganizations(BusinessTypes businessTypes, Pageable pageable) {

        Page<BusinessDTOForUI> organizations = null;
        if (businessTypes.equals(BusinessTypes.MARKET) || businessTypes.equals(BusinessTypes.RESTAURANT))
            organizations = organizationRepository.getAllOrganizationsByBusinessType(businessTypes, pageable);
        else
            throw new BusinessTypeDoesNotRecognisedException(businessTypes + " is not recognised");
        return new ResponseEntity<>(new CustomPage<>(organizations.getContent(), pageable, organizations.getTotalElements()), HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<ProductDTO> createProduct(UUID organizationId, UUID menuId, ProductDTO productDTO) {
        //TODO: product code gotto be unique add constraint!
        var organization = organizationRepository.findOrganizationByExactOrganizationId(organizationId);

        if (Objects.isNull(organization))
            throw new BusinessNotFoundException("Business not found!");

        var product = productMapper.toEntity(productDTO);

        organization.getMenuList()
                .stream().filter(menu -> menu.getId().equals(menuId))
                .findFirst().orElseThrow(MenuNotFoundException::new).getProductList().add(product);

        organizationRepository.save(organization);
        organizationRepository.flush();
        //TODO: getProductCode might not be unique. Find another solution about this!
        product = productRepository.findProductByProductCode(product.getProductCode());
        productDTO.setId(product.getId());
        stockClient.updateStock(new CreateStockDTO(product.getId(), 0L));

        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<UpdateProductDTO> updateProduct(UUID organizationId, UUID menuId, UUID productId, UpdateProductDTO updateProductDTO, BusinessTypes businessTypes) {

        var organization = organizationRepository.findOrganizationByExactOrganizationId(organizationId);

        if (Objects.isNull(organization))
            throw new BusinessNotFoundException("Business not found!");
        if (organization.getMenuList().stream().filter(menu -> menu.getId().equals(menuId)).findFirst().isEmpty())
            return new ResponseEntity<>(updateProductDTO, HttpStatus.NOT_FOUND);
        //TODO: wtf :D?

        return null;
    }

    @Override
    @Transactional
    public ResponseEntity<Void> deleteProduct(UUID organizationId, UUID productId, UUID menuId) {

        var organization = organizationRepository.findOrganizationByExactOrganizationId(organizationId);

        if (Objects.isNull(organization))
            throw new BusinessNotFoundException("Business not found!");
        if (organization.getMenuList().stream().filter(menu -> menu.getId().equals(menuId)).findFirst().isEmpty())
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        if (organization.getMenuList().stream()
                .filter(menu -> menu.getId().equals(menuId))
                .findFirst().get().getProductList().stream().filter(prod -> prod.getId().equals(productId)).findFirst().isEmpty())
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);


        organization.getMenuList().forEach(menu -> {
            menu.getProductList().removeIf(prod -> prod.getId().equals(productId));
        });
        organizationRepository.save(organization);


        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductDTO> getProduct(UUID organizationId, UUID productId) {
        var organization = organizationRepository.findOrganizationByExactOrganizationId(organizationId);

        if (Objects.isNull(organization))
            throw new BusinessNotFoundException("Business not found!");

        ProductDTO dto = extractDtoFromOrganization(organization, productId);
        if (!Objects.isNull(dto))
            return new ResponseEntity<>(dto, HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    private ProductDTO extractDtoFromOrganization(Organization organization, UUID productId) {

        if (!Objects.isNull(organization)) {
            var selectedProduct = organization.getMenuList().stream().flatMap(menu -> menu.getProductList().stream()).filter(prod -> prod.getId().equals(productId)).findFirst();
            if (selectedProduct.isPresent()) {
                var dto = productMapper.toDto(selectedProduct.get());
                return dto;
            }
        }

        return null;

    }


    @Override
    public ResponseEntity<CustomPage<ProductDTO>> getProducts(UUID organizationId, UUID menuID, Pageable pageable) {

        var organization = organizationRepository.findOrganizationByExactOrganizationId(organizationId);

        if (Objects.isNull(organization))
            throw new BusinessNotFoundException("Business not found");
        var product = productRepository.getAllProducts(organization.getExactOrganizationId(), menuID, pageable);

        var productDto = productMapper.toDto(product.getContent());
        Page<ProductDTO> page = new PageImpl<>(productDto);

        return new ResponseEntity<>(new CustomPage<>(page.getContent(), pageable, page.getTotalElements()), HttpStatus.OK);

    }

    @Override
    @Transactional
    public ResponseEntity<OrderDTO> createOrder(OrderDTO orderDTO) {

        var containsAll = productRepository.existsAllByIdIn(orderDTO.getProductId());

        if (!containsAll) {
            throw new MissingProductsException("Some of the products are not valid!");
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
