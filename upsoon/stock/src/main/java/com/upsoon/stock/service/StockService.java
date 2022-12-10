package com.upsoon.stock.service;

import com.upsoon.common.dto.Stock.CreateStockDTO;
import com.upsoon.common.kafkaTemplateDTO.OrderToStock;
import com.upsoon.common.kafkaTemplateDTO.StockToPayment;
import org.springframework.http.ResponseEntity;

/**
 * @author burak.yesildal
 */

public interface StockService {

    void save(OrderToStock orderToStock);

    void rollback(StockToPayment stockToPayment);

    ResponseEntity<CreateStockDTO> createStock(CreateStockDTO createStockDTO);

    ResponseEntity<CreateStockDTO> updateStock(CreateStockDTO createStockDTO);


}
