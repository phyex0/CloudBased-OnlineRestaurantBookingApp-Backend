package com.upsoon.order.controller;

import com.upsoon.common.dto.Order.*;
import com.upsoon.common.enums.BusinessTypes;
import com.upsoon.common.web.CustomPage;
import com.upsoon.order.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author Halit Burak Yeşildal
 */


@RestController
@RequestMapping("/api/order")
public class OrderServiceController {


    private final OrderService orderService;

    public OrderServiceController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/menu")
    public ResponseEntity<MenuDTO> createMenu(@RequestParam UUID organizationId, @RequestParam BusinessTypes businessTypes, @RequestBody MenuDTO menuDTO) {
        return orderService.createMenu(organizationId, businessTypes, menuDTO);
    }

    @PutMapping("/menu")
    public ResponseEntity<UpdateMenuDTO> updateMenu(@RequestParam UUID organizationId, @RequestParam UUID menuId, @RequestParam BusinessTypes businessTypes, @RequestBody UpdateMenuDTO menuDTO) {
        return orderService.updateMenu(organizationId, menuId, businessTypes, menuDTO);
    }

    @DeleteMapping("/menu")
    public ResponseEntity<Void> deleteMenu(@RequestParam UUID organizationId, @RequestParam UUID menuId) {
        return orderService.deleteMenu(organizationId, menuId);
    }

    @GetMapping("/menu")
    public ResponseEntity<CustomPage<MenuDTO>> getMenu(@RequestParam UUID organizationId, @RequestParam BusinessTypes businessTypes, Pageable pageable) {
        return orderService.getMenu(organizationId, businessTypes, pageable);
    }

    @GetMapping
    public ResponseEntity<OrganizationDTO> getOrganization(@RequestParam UUID organizationId) {
        return orderService.getOrganization(organizationId);
    }

    @GetMapping("/business")
    public ResponseEntity<CustomPage<BusinessDTOForUI>> getAllOrganizations(@RequestParam BusinessTypes businessTypes, Pageable pageable) {
        return orderService.getAllOrganizations(businessTypes, pageable);
    }

    @PostMapping("/product")
    public ResponseEntity<ProductDTO> createProduct(@RequestParam UUID organizationId, @RequestParam UUID menuId, @RequestBody ProductDTO productDTO, @RequestParam BusinessTypes businessTypes) {
        return orderService.createProduct(organizationId, menuId, productDTO, businessTypes);
    }

    @PutMapping("/product")
    public ResponseEntity<UpdateProductDTO> updateProduct(@RequestParam UUID organizationId, @RequestParam UUID menuId, @RequestParam UUID productId, @RequestBody UpdateProductDTO updateProductDTO, @RequestParam BusinessTypes businessTypes) {
        return orderService.updateProduct(organizationId, menuId, productId, updateProductDTO, businessTypes);
    }

    @DeleteMapping("/product")
    public ResponseEntity<Void> deleteProduct(@RequestParam UUID organizationId, @RequestParam UUID productId, @RequestParam UUID menuId, @RequestParam BusinessTypes businessTypes) {
        return orderService.deleteProduct(organizationId, productId, menuId, businessTypes);
    }

    @GetMapping("/product")
    public ResponseEntity<ProductDTO> getProduct(@RequestParam UUID organizationId, @RequestParam UUID productId) {
        return orderService.getProduct(organizationId, productId);
    }

    @GetMapping("/product-list")
    public ResponseEntity<CustomPage<ProductDTO>> getProducts(@RequestParam UUID organizationId, @RequestParam UUID menuID, @RequestParam BusinessTypes businessTypes, Pageable pageable) {
        return orderService.getProducts(organizationId, menuID, businessTypes, pageable);
    }


    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.createOrder(orderDTO);
    }


}
