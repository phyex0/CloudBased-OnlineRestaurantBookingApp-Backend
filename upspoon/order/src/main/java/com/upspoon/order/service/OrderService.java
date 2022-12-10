package com.upspoon.order.service;

import com.upspoon.common.dto.Order.*;
import com.upspoon.common.enums.BusinessTypes;
import com.upspoon.common.kafkaTemplateDTO.OrderToStock;
import com.upspoon.common.kafkaTemplateDTO.OrganizationToOrder;
import com.upspoon.common.web.CustomPage;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

/**
 * @author burak.yesildal
 */

public interface OrderService {

    void saveOrganization(OrganizationToOrder organizationToOrder);

    void rollbackOrder(OrderToStock orderToStock);


    ResponseEntity<MenuDTO> createMenu(UUID organizationId, BusinessTypes businessTypes, MenuDTO menuDTO);


    ResponseEntity<UpdateMenuDTO> updateMenu(UUID organizationId, UUID menuId, BusinessTypes businessTypes, UpdateMenuDTO menuDTO);

    ResponseEntity<Void> deleteMenu(UUID organizationId, UUID menuId);


    ResponseEntity<CustomPage<MenuDTO>> getMenu(UUID organizationId, BusinessTypes businessTypes, Pageable pageable);


    ResponseEntity<OrganizationDTO> getOrganization(UUID organizationId);


    ResponseEntity<CustomPage<BusinessDTOForUI>> getAllOrganizations(BusinessTypes businessTypes, Pageable pageable);


    ResponseEntity<ProductDTO> createProduct(UUID organizationId, UUID menuId, ProductDTO productDTO, BusinessTypes businessTypes);


    ResponseEntity<UpdateProductDTO> updateProduct(UUID organizationId, UUID menuId, UUID productId, UpdateProductDTO updateProductDTO, BusinessTypes businessTypes);


    ResponseEntity<Void> deleteProduct(UUID organizationId, UUID productId, UUID menuId, BusinessTypes businessTypes);


    ResponseEntity<ProductDTO> getProduct(UUID organizationId, UUID productId);


    ResponseEntity<CustomPage<ProductDTO>> getProducts(UUID organizationId, UUID menuID, BusinessTypes businessTypes, Pageable pageable);

    ResponseEntity<OrderDTO> createOrder(OrderDTO orderDTO);

    ResponseEntity<CustomPage<OrderHistoryDTO>> orderHistory(UUID userId, Pageable pageable);

}