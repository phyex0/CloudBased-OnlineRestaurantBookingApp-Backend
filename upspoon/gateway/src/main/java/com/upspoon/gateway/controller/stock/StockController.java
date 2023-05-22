package com.upspoon.gateway.controller.stock;

import com.upspoon.common.dto.Stock.CreateStockDTO;
import com.upspoon.gateway.client.stock.StockClient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author burak.yesildal
 */

@RestController
@RequestMapping("/stock/api/")
@Tag(name = "Stock Controller")
public class StockController {

    private final StockClient stockClient;

    public StockController(StockClient stockClient) {
        this.stockClient = stockClient;
    }

    @PostMapping
    @PreAuthorize("hasPermission('ROLE', 'ORGANIZATION_ROLE') or hasPermission('ROLE','ADMIN_ROLE')")
    @Operation(summary = "create stock by createStockDto")
    public ResponseEntity<CreateStockDTO> createStock(@RequestBody CreateStockDTO createStockDTO) {
        return stockClient.createStock(createStockDTO);
    }

    @PutMapping
    @PreAuthorize("hasPermission('ROLE', 'ORGANIZATION_ROLE') or hasPermission('ROLE','ADMIN_ROLE')")
    @Operation(summary = "update stocks by create stockDto")
    public ResponseEntity<CreateStockDTO> updateStock(@RequestBody CreateStockDTO createStockDTO) {
        return stockClient.updateStock(createStockDTO);
    }
}
