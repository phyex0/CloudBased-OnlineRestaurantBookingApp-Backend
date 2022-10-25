package com.upsoon.order.repository;

import com.upsoon.order.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @author Halit Burak Yeşildal
 */


public interface ProductRepository extends JpaRepository<Product, UUID> {
}
