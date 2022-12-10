package com.upspoon.stock.controller;


import com.upspoon.common.dto.Stock.CreateStockDTO;
import com.upspoon.stock.service.StockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stock")
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping
    public ResponseEntity<CreateStockDTO> createStock(@RequestBody CreateStockDTO createStockDTO) {
        return stockService.createStock(createStockDTO);
    }

    @PutMapping
    public ResponseEntity<CreateStockDTO> updateStock(@RequestBody CreateStockDTO createStockDTO) {
        return stockService.updateStock(createStockDTO);
    }
}
