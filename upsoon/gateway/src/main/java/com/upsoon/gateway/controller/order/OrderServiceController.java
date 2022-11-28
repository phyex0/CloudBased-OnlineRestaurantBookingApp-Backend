package com.upsoon.gateway.controller.order;

import com.upsoon.common.dto.Order.*;
import com.upsoon.common.enums.BusinessTypes;
import com.upsoon.gateway.client.order.OrderServiceClient;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author Halit Burak Yeşildal
 */

@RestController
@RequestMapping("/order-service/api")
@Api(value = "Order Controller")
public class OrderServiceController {

    private final OrderServiceClient orderServiceClient;

    public OrderServiceController(OrderServiceClient orderServiceClient) {
        this.orderServiceClient = orderServiceClient;
    }

    @PostMapping("/menu")
    public ResponseEntity<MenuDTO> createMenu(@RequestParam UUID organizationId, @RequestParam BusinessTypes businessTypes, @RequestBody MenuDTO menuDTO) {
        return orderServiceClient.createMenu(organizationId, businessTypes, menuDTO);
    }

    @PutMapping("/menu")
    public ResponseEntity<UpdateMenuDTO> updateMenu(@RequestParam UUID organizationId, @RequestParam UUID menuId, @RequestParam BusinessTypes businessTypes, @RequestBody UpdateMenuDTO menuDTO) {
        return orderServiceClient.updateMenu(organizationId, menuId, businessTypes, menuDTO);
    }

    @DeleteMapping("/menu")
    public ResponseEntity<Void> deleteMenu(@RequestParam UUID organizationId, @RequestParam UUID menuId) {
        return orderServiceClient.deleteMenu(organizationId, menuId);
    }

    @GetMapping("/menu")
    public ResponseEntity<Page<MenuDTO>> getMenu(@RequestParam UUID organizationId, @RequestParam BusinessTypes businessTypes, Pageable pageable) {
        return orderServiceClient.getMenu(organizationId, businessTypes, pageable);
    }

    @GetMapping
    public ResponseEntity<OrganizationDTO> getOrganization(@RequestParam UUID organizationId) {
        return orderServiceClient.getOrganization(organizationId);
    }

    @GetMapping("/business")
    public ResponseEntity<Page<BusinessDTOForUI>> getAllOrganizations(@RequestParam BusinessTypes businessTypes, Pageable pageable) {
        return orderServiceClient.getAllOrganizations(businessTypes, pageable);
    }

    @PostMapping("/product")
    public ResponseEntity<ProductDTO> createProduct(@RequestParam UUID organizationId, @RequestParam UUID menuId, @RequestBody ProductDTO productDTO, @RequestParam BusinessTypes businessTypes) {
        return orderServiceClient.createProduct(organizationId, menuId, productDTO, businessTypes);
    }

    @PutMapping("/product")
    public ResponseEntity<UpdateProductDTO> updateProduct(@RequestParam UUID organizationId, @RequestParam UUID menuId, @RequestParam UUID productId, @RequestBody UpdateProductDTO updateProductDTO, @RequestParam BusinessTypes businessTypes) {
        return orderServiceClient.updateProduct(organizationId, menuId, productId, updateProductDTO, businessTypes);
    }

    @DeleteMapping("/product")
    public ResponseEntity<Void> deleteProduct(@RequestParam UUID organizationId, @RequestParam UUID productId, @RequestParam UUID menuId, @RequestParam BusinessTypes businessTypes) {
        return orderServiceClient.deleteProduct(organizationId, productId, menuId, businessTypes);
    }

    @GetMapping("/product")
    public ResponseEntity<ProductDTO> getProduct(@RequestParam UUID organizationId, @RequestParam UUID productId) {
        return orderServiceClient.getProduct(organizationId, productId);
    }

    @GetMapping("/product-list")
    public ResponseEntity<Page<ProductDTO>> getProducts(@RequestParam UUID organizationId, @RequestParam UUID menuID, @RequestParam BusinessTypes businessTypes, Pageable pageable) {
        return orderServiceClient.getProducts(organizationId, menuID, businessTypes, pageable);
    }


    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        return orderServiceClient.createOrder(orderDTO);
    }

}
