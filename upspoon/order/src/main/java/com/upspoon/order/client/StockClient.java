package com.upspoon.order.client;

import com.upspoon.common.config.CustomFeignConfiguration;
import com.upspoon.common.dto.Stock.CreateStockDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author burak.yesildal
 */

@FeignClient(name = "${client.stock-api.name}", url = "${client.stock-api.url}", configuration = CustomFeignConfiguration.class)
public interface StockClient {

    @PostMapping("/stock")
    ResponseEntity<CreateStockDTO> createStock(@RequestBody CreateStockDTO createStockDTO);
}
