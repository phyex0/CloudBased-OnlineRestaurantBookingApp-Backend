package com.upsoon.stock.repository;

import com.upsoon.stock.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * @author Halit Burak Yeşildal
 */

@Repository
public interface StockRepository extends JpaRepository<Stock, UUID> {


    List<Stock> findAllByProductIdIn(List<UUID> productIdList);
}
