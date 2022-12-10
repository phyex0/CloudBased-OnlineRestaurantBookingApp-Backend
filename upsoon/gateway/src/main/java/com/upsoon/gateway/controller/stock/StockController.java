package com.upsoon.gateway.controller.stock;

import com.upsoon.common.dto.Stock.CreateStockDTO;
import com.upsoon.gateway.client.stock.StockClient;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author burak.yesildal
 */

@RestController
@RequestMapping("/stock/api/")
@Api(value = "Stock Controller")
public class StockController {

    private final StockClient stockClient;

    public StockController(StockClient stockClient) {
        this.stockClient = stockClient;
    }

    @PostMapping
    @Operation(summary = "create stock by createStockDto")
    public ResponseEntity<CreateStockDTO> createStock(@RequestBody CreateStockDTO createStockDTO) {
        return stockClient.createStock(createStockDTO);
    }

    @PutMapping
    @Operation(summary = "update stocks by create stockDto")
    public ResponseEntity<CreateStockDTO> updateStock(@RequestBody CreateStockDTO createStockDTO) {
        return stockClient.updateStock(createStockDTO);
    }
}
