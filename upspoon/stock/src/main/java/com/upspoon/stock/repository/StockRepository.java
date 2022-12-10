package com.upspoon.stock.repository;

import com.upspoon.stock.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * @author burak.yesildal
 */

@Repository
public interface StockRepository extends JpaRepository<Stock, UUID> {


    List<Stock> findAllByProductIdIn(List<UUID> productIdList);
}
