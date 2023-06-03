package com.upspoon.stock.service;

import com.upspoon.common.dto.Stock.CreateStockDTO;
import com.upspoon.common.kafkaTemplateDTO.OrderToStock;
import com.upspoon.common.kafkaTemplateDTO.StockToPayment;
import org.springframework.http.ResponseEntity;

/**
 * @author burak.yesildal
 */

public interface StockService {

    void save(OrderToStock orderToStock);

    void rollback(StockToPayment stockToPayment);
    ResponseEntity<CreateStockDTO> updateStock(CreateStockDTO createStockDTO);
}
