package com.upspoon.order.client;

import com.upspoon.common.config.CustomFeignConfiguration;
import com.upspoon.common.dto.Stock.CreateStockDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * @author burak.yesildal
 */

@FeignClient(name = "${client.stock-api.name}", url = "${client.stock-api.url}", configuration = CustomFeignConfiguration.class)
public interface StockClient {

    @PutMapping("/stock")
    ResponseEntity<CreateStockDTO> updateStock(@RequestBody CreateStockDTO createStockDTO);

    @GetMapping("/stock")
    ResponseEntity<Map<UUID, Long>> getStockMap(@RequestParam Collection<UUID> productId);
}
