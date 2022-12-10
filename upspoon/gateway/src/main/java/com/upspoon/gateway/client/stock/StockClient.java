package com.upspoon.gateway.client.stock;

import com.upspoon.common.config.CustomFeignConfiguration;
import com.upspoon.common.dto.Stock.CreateStockDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author burak.yesildal
 */

@FeignClient(name = "${client.stock-api.name}", url = "${client.stock-api.url}", configuration = CustomFeignConfiguration.class)
public interface StockClient {

    @PostMapping(value = "/stock", produces = "application/json", consumes = "application/json")
    ResponseEntity<CreateStockDTO> createStock(@RequestBody CreateStockDTO createStockDTO);

    @PutMapping(value = "/stock", produces = "application/json", consumes = "application/json")
    ResponseEntity<CreateStockDTO> updateStock(@RequestBody CreateStockDTO createStockDTO);
}
