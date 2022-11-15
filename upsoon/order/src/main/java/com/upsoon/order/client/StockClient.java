package com.upsoon.order.client;

import com.upsoon.common.dto.Stock.CreateStockDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Halit Burak Yeşildal
 */

@FeignClient(name = "${client.stock-api.name}", url = "${client.stock-api.url}" /*,configuration*/)
public interface StockClient {

    @PostMapping("/stock")
    ResponseEntity<CreateStockDTO> createStock(@RequestBody CreateStockDTO createStockDTO);
}
