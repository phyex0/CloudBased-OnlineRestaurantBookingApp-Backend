package com.upspoon.stock.controller;


import com.upspoon.common.dto.Stock.CreateStockDTO;
import com.upspoon.stock.service.StockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/stock")
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PutMapping
    public ResponseEntity<CreateStockDTO> updateStock(@RequestBody CreateStockDTO createStockDTO) {
        return stockService.updateStock(createStockDTO);
    }

    @GetMapping
    public ResponseEntity<Map<UUID, Long>> getStockMap(@RequestParam Set<UUID> productId) {
        return stockService.getStockMap(productId);
    }
}
