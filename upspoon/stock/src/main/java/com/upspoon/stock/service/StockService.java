package com.upspoon.stock.service;

import com.upspoon.common.dto.Stock.CreateStockDTO;
import com.upspoon.common.kafkaTemplateDTO.OrderToStock;
import com.upspoon.common.kafkaTemplateDTO.StockToPayment;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * @author burak.yesildal
 */

public interface StockService {

    void save(OrderToStock orderToStock);

    void rollback(StockToPayment stockToPayment);

    ResponseEntity<CreateStockDTO> updateStock(CreateStockDTO createStockDTO);

    ResponseEntity<Map<UUID, Long>> getStockMap(Set<UUID> productId);
}
