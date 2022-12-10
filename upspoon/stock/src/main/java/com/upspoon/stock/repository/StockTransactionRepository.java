package com.upspoon.stock.repository;

import com.upspoon.stock.model.StockTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author burak.yesildal
 */

@Repository
public interface StockTransactionRepository extends JpaRepository<StockTransaction, UUID> {

    StockTransaction findByOrderId(UUID orderId);

}
