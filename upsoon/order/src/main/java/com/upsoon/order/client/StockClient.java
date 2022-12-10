package com.upsoon.order.client;

import com.upsoon.common.config.CustomFeignConfiguration;
import com.upsoon.common.dto.Stock.CreateStockDTO;
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
