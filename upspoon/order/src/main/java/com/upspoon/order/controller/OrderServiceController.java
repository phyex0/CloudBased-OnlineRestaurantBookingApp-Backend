package com.upspoon.order.controller;

import com.upspoon.common.dto.Order.*;
import com.upspoon.common.enums.BusinessTypes;
import com.upspoon.common.web.CustomPage;
import com.upspoon.order.service.OrderService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * @author burak.yesildal
 */


@RestController
@RequestMapping("/api/order")
public class OrderServiceController {


    private final OrderService orderService;

    public OrderServiceController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/menu")
    public ResponseEntity<MenuDTO> createMenu(@RequestParam UUID organizationId, @RequestBody MenuDTO menuDTO) {
        return orderService.createMenu(organizationId, menuDTO);
    }

    @PutMapping("/menu")
    public ResponseEntity<UpdateMenuDTO> updateMenu(@RequestParam UUID organizationId, @RequestParam UUID menuId, @RequestBody UpdateMenuDTO menuDTO) {
        return orderService.updateMenu(organizationId, menuId, menuDTO);
    }

    @DeleteMapping("/menu")
    public ResponseEntity<Void> deleteMenu(@RequestParam UUID organizationId, @RequestParam UUID menuId) {
        return orderService.deleteMenu(organizationId, menuId);
    }

    @GetMapping("/menu")
    public ResponseEntity<List<MenuDTO>> getMenu(@RequestParam UUID organizationId) {
        return orderService.getMenu(organizationId);
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
    public ResponseEntity<ProductDTO> createProduct(@RequestParam UUID organizationId, @RequestParam UUID menuId, @RequestBody ProductDTO productDTO) {
        return orderService.createProduct(organizationId, menuId, productDTO);
    }

    @PutMapping("/product")
    public ResponseEntity<UpdateProductDTO> updateProduct(@RequestParam UUID organizationId, @RequestParam UUID menuId, @RequestParam UUID productId, @RequestBody UpdateProductDTO updateProductDTO, @RequestParam BusinessTypes businessTypes) {
        return orderService.updateProduct(organizationId, menuId, productId, updateProductDTO, businessTypes);
    }

    @DeleteMapping("/product")
    public ResponseEntity<Void> deleteProduct(@RequestParam UUID organizationId, @RequestParam UUID productId, @RequestParam UUID menuId) {
        return orderService.deleteProduct(organizationId, productId, menuId);
    }

    @GetMapping("/product")
    public ResponseEntity<ProductDTO> getProduct(@RequestParam UUID organizationId, @RequestParam UUID productId) {
        return orderService.getProduct(organizationId, productId);
    }

    @GetMapping("/product-list")
    public ResponseEntity<CustomPage<ProductDTO>> getProducts(@RequestParam UUID organizationId, @RequestParam UUID menuID, Pageable pageable) {
        return orderService.getProducts(organizationId, menuID, pageable);
    }


    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.createOrder(orderDTO);
    }

    @GetMapping("get-order-history")
    public ResponseEntity<CustomPage<OrderHistoryDTO>> orderHistory(@RequestParam("user-id") UUID userId, Pageable pageable) {
        return orderService.orderHistory(userId, pageable);
    }

}
