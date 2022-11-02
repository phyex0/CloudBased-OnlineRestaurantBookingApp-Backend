package com.upsoon.order.service;

import com.upsoon.common.dto.Order.*;
import com.upsoon.common.enums.BusinessTypes;
import com.upsoon.common.kafkaTemplateDTO.OrganizationToOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author Halit Burak Yeşildal
 */

public interface OrderService {

    void saveOrganization(OrganizationToOrder organizationToOrder);


    ResponseEntity<MenuDTO> createMenu(UUID organizationId, BusinessTypes businessTypes, MenuDTO menuDTO);


    ResponseEntity<UpdateMenuDTO> updateMenu(UUID organizationId, UUID menuId, BusinessTypes businessTypes, UpdateMenuDTO menuDTO);

    ResponseEntity<Void> deleteMenu(UUID organizationId, UUID menuId);


    ResponseEntity<Page<MenuDTO>> getMenu(UUID organizationId, BusinessTypes businessTypes, Pageable pageable);


    ResponseEntity<OrganizationDTO> getOrganization(UUID organizationId);


    ResponseEntity<Page<BusinessDTOForUI>> getAllOrganizations(BusinessTypes businessTypes, Pageable pageable);


    ResponseEntity<ProductDTO> createProduct(UUID organizationId, UUID menuId, ProductDTO productDTO);


    ResponseEntity<UpdateProductDTO> updateProduct(UUID organizationId, UUID productId, UpdateProductDTO updateProductDTO);


    ResponseEntity<Void> deleteProduct(UUID organizationId, UUID productId);


    ResponseEntity<ProductDTO> getProduct(UUID organizationId, UUID productId);


    ResponseEntity<Page<ProductDTO>> getProducts(UUID organizationId, BusinessTypes businessTypes);


}
