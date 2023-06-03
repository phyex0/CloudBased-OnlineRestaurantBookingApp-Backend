package com.upspoon.stock.controller;


import com.upspoon.common.dto.Stock.CreateStockDTO;
import com.upspoon.stock.service.StockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
