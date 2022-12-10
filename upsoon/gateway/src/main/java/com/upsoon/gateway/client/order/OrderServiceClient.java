package com.upsoon.gateway.client.order;

import com.upsoon.common.config.CustomFeignConfiguration;
import com.upsoon.common.dto.Order.*;
import com.upsoon.common.enums.BusinessTypes;
import com.upsoon.common.web.CustomPage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author burak.yesildal
 */

@FeignClient(name = "${client.order-api.name}", url = "${client.order-api.url}", configuration = CustomFeignConfiguration.class)
public interface OrderServiceClient {

    @PostMapping(value = "/api/order/menu", produces = "application/json", consumes = "application/json")
    ResponseEntity<MenuDTO> createMenu(@RequestParam UUID organizationId, @RequestParam BusinessTypes businessTypes, @RequestBody MenuDTO menuDTO);

    @PutMapping(value = "/api/order/menu", produces = "application/json", consumes = "application/json")
    ResponseEntity<UpdateMenuDTO> updateMenu(@RequestParam UUID organizationId, @RequestParam UUID menuId, @RequestParam BusinessTypes businessTypes, @RequestBody UpdateMenuDTO menuDTO);

    @DeleteMapping(value = "/api/order/menu", produces = "application/json", consumes = "application/json")
    ResponseEntity<Void> deleteMenu(@RequestParam UUID organizationId, @RequestParam UUID menuId);

    @GetMapping(value = "/api/order/menu", produces = "application/json", consumes = "application/json")
    ResponseEntity<CustomPage<MenuDTO>> getMenu(@RequestParam UUID organizationId, @RequestParam BusinessTypes businessTypes, Pageable pageable);

    @GetMapping(value = "/api/order", produces = "application/json", consumes = "application/json")
    ResponseEntity<OrganizationDTO> getOrganization(@RequestParam UUID organizationId);

    @GetMapping(value = "/api/order/business", produces = "application/json", consumes = "application/json")
    ResponseEntity<CustomPage<BusinessDTOForUI>> getAllOrganizations(@RequestParam BusinessTypes businessTypes, Pageable pageable);

    @PostMapping(value = "/api/order/product", produces = "application/json", consumes = "application/json")
    ResponseEntity<ProductDTO> createProduct(@RequestParam UUID organizationId, @RequestParam UUID menuId, @RequestBody ProductDTO productDTO, @RequestParam BusinessTypes businessTypes);

    @PutMapping(value = "/api/order/product", produces = "application/json", consumes = "application/json")
    ResponseEntity<UpdateProductDTO> updateProduct(@RequestParam UUID organizationId, @RequestParam UUID menuId, @RequestParam UUID productId, @RequestBody UpdateProductDTO updateProductDTO, @RequestParam BusinessTypes businessTypes);

    @DeleteMapping(value = "/api/order/product", produces = "application/json", consumes = "application/json")
    ResponseEntity<Void> deleteProduct(@RequestParam UUID organizationId, @RequestParam UUID productId, @RequestParam UUID menuId, @RequestParam BusinessTypes businessTypes);

    @GetMapping(value = "/api/order/product", produces = "application/json", consumes = "application/json")
    ResponseEntity<ProductDTO> getProduct(@RequestParam UUID organizationId, @RequestParam UUID productId);

    @GetMapping(value = "/api/order/product-list", produces = "application/json", consumes = "application/json")
    ResponseEntity<CustomPage<ProductDTO>> getProducts(@RequestParam UUID organizationId, @RequestParam UUID menuID, @RequestParam BusinessTypes businessTypes, Pageable pageable);


    @PostMapping(value = "/api/order", produces = "application/json", consumes = "application/json")
    ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO);

    @GetMapping("/api/order/get-order-history")
    ResponseEntity<CustomPage<OrderHistoryDTO>> orderHistory(@RequestParam("user-id") UUID userId, Pageable pageable);

}
