package com.upspoon.gateway.controller.order;

import com.upspoon.common.dto.Order.*;
import com.upspoon.common.enums.BusinessTypes;
import com.upspoon.common.web.CustomPage;
import com.upspoon.gateway.client.order.OrderServiceClient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author burak.yesildal
 */

@RestController
@RequestMapping("/order-service/api")
@Tag(name = "Order Controller")
public class OrderServiceController {

    private final OrderServiceClient orderServiceClient;

    public OrderServiceController(OrderServiceClient orderServiceClient) {
        this.orderServiceClient = orderServiceClient;
    }

    @PostMapping("/menu")
    @Operation(summary = "Create menu by organization id, and menuDto")
    public ResponseEntity<MenuDTO> createMenu(@RequestParam UUID organizationId, @RequestBody MenuDTO menuDTO) {
        return orderServiceClient.createMenu(organizationId, menuDTO);
    }

    @PutMapping("/menu")
    @Operation(summary = "update menu by organization id and update dto")
    public ResponseEntity<UpdateMenuDTO> updateMenu(@RequestParam UUID organizationId, @RequestParam UUID menuId, @RequestBody UpdateMenuDTO menuDTO) {
        return orderServiceClient.updateMenu(organizationId, menuId, menuDTO);
    }

    @DeleteMapping("/menu")
    @Operation(summary = "delete menu by menu id and organization id")
    public ResponseEntity<Void> deleteMenu(@RequestParam UUID organizationId, @RequestParam UUID menuId) {
        return orderServiceClient.deleteMenu(organizationId, menuId);
    }

    @GetMapping("/menu")
    @Operation(summary = "get menu as pageable by organization id, business type")
    public ResponseEntity<CustomPage<MenuDTO>> getMenu(@RequestParam UUID organizationId, Pageable pageable) {
        return orderServiceClient.getMenu(organizationId, pageable);
    }

    @GetMapping
    @Operation(summary = "get organization by organization id")
    public ResponseEntity<OrganizationDTO> getOrganization(@RequestParam UUID organizationId) {
        return orderServiceClient.getOrganization(organizationId);
    }

    @GetMapping("/business")
    @PreAuthorize("hasPermission('ROLE', 'ORGANIZATION_ROLE') or hasPermission('ROLE','ADMIN_ROLE')"  )
    @Operation(summary = "get all organizations by business type with pageable")
    public ResponseEntity<CustomPage<BusinessDTOForUI>> getAllOrganizations(@RequestParam BusinessTypes businessTypes, Pageable pageable) {
        return orderServiceClient.getAllOrganizations(businessTypes, pageable);
    }

    @PostMapping("/product")
    @Operation(summary = "create product by organization id and menu id, with menuId with productDto")
    public ResponseEntity<ProductDTO> createProduct(@RequestParam UUID organizationId, @RequestParam UUID menuId, @RequestBody ProductDTO productDTO) {
        return orderServiceClient.createProduct(organizationId, menuId, productDTO);
    }

    @PutMapping("/product")
    @Operation(summary = "update product by organization id and menu id with productDTO")
    public ResponseEntity<UpdateProductDTO> updateProduct(@RequestParam UUID organizationId, @RequestParam UUID menuId, @RequestParam UUID productId, @RequestBody UpdateProductDTO updateProductDTO, @RequestParam BusinessTypes businessTypes) {
        return orderServiceClient.updateProduct(organizationId, menuId, productId, updateProductDTO, businessTypes);
    }

    @DeleteMapping("/product")
    @Operation(summary = "delete product by organization id, menu id and productId")
    public ResponseEntity<Void> deleteProduct(@RequestParam UUID organizationId, @RequestParam UUID productId, @RequestParam UUID menuId) {
        return orderServiceClient.deleteProduct(organizationId, productId, menuId);
    }

    @GetMapping("/product")
    @Operation(summary = "get product by organization id and product id")
    public ResponseEntity<ProductDTO> getProduct(@RequestParam UUID organizationId, @RequestParam UUID productId) {
        return orderServiceClient.getProduct(organizationId, productId);
    }

    @GetMapping("/product-list")
    @Operation(summary = "get products as pageable by organization id, menu id and pageable object")
    public ResponseEntity<CustomPage<ProductDTO>> getProducts(@RequestParam UUID organizationId, @RequestParam UUID menuID, Pageable pageable) {
        return orderServiceClient.getProducts(organizationId, menuID, pageable);
    }


    @PostMapping
    @Operation(summary = "create order with orderDto")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        return orderServiceClient.createOrder(orderDTO);
    }

    @GetMapping("get-order-history")
    @Operation(summary = "get all orders as pageable by userId and pageable")
    public ResponseEntity<CustomPage<OrderHistoryDTO>> orderHistory(@RequestParam("user-id") UUID userId, Pageable pageable) {
        return orderServiceClient.orderHistory(userId, pageable);
    }

}
