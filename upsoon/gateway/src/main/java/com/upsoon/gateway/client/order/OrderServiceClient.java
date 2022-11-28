package com.upsoon.gateway.client.order;

import com.upsoon.common.dto.Order.*;
import com.upsoon.common.enums.BusinessTypes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author Halit Burak Yeşildal
 */

@FeignClient(name = "${client.order-api.name}", url = "${client.order-api.url}" /*,configuration*/)
public interface OrderServiceClient {

    @PostMapping(value = "/api/order/menu")
    ResponseEntity<MenuDTO> createMenu(@RequestParam UUID organizationId, @RequestParam BusinessTypes businessTypes, @RequestBody MenuDTO menuDTO);

    @PutMapping(value = "/api/order/menu")
    ResponseEntity<UpdateMenuDTO> updateMenu(@RequestParam UUID organizationId, @RequestParam UUID menuId, @RequestParam BusinessTypes businessTypes, @RequestBody UpdateMenuDTO menuDTO);

    @DeleteMapping(value = "/api/order/menu")
    ResponseEntity<Void> deleteMenu(@RequestParam UUID organizationId, @RequestParam UUID menuId);

    @GetMapping(value = "/api/order/menu")
    ResponseEntity<Page<MenuDTO>> getMenu(@RequestParam UUID organizationId, @RequestParam BusinessTypes businessTypes, Pageable pageable);

    @GetMapping(value = "/api/order")
    ResponseEntity<OrganizationDTO> getOrganization(@RequestParam UUID organizationId);

    @GetMapping(value = "/api/order/business")
    ResponseEntity<Page<BusinessDTOForUI>> getAllOrganizations(@RequestParam BusinessTypes businessTypes, Pageable pageable);

    @PostMapping(value = "/api/order/product")
    ResponseEntity<ProductDTO> createProduct(@RequestParam UUID organizationId, @RequestParam UUID menuId, @RequestBody ProductDTO productDTO, @RequestParam BusinessTypes businessTypes);

    @PutMapping(value = "/api/order/product")
    ResponseEntity<UpdateProductDTO> updateProduct(@RequestParam UUID organizationId, @RequestParam UUID menuId, @RequestParam UUID productId, @RequestBody UpdateProductDTO updateProductDTO, @RequestParam BusinessTypes businessTypes);

    @DeleteMapping(value = "/api/order/product")
    ResponseEntity<Void> deleteProduct(@RequestParam UUID organizationId, @RequestParam UUID productId, @RequestParam UUID menuId, @RequestParam BusinessTypes businessTypes);

    @GetMapping(value = "/api/order/product")
    ResponseEntity<ProductDTO> getProduct(@RequestParam UUID organizationId, @RequestParam UUID productId);

    @GetMapping(value = "/api/order/product-list")
    ResponseEntity<Page<ProductDTO>> getProducts(@RequestParam UUID organizationId, @RequestParam UUID menuID, @RequestParam BusinessTypes businessTypes, Pageable pageable);


    @PostMapping(value = "/api/order")
    ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO);

}
