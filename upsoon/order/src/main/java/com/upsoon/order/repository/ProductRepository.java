package com.upsoon.order.repository;

import com.upsoon.order.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author Halit Burak Yeşildal
 */

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    @Query("select p from Business b left join  b.menuList m left  join  m.productList p " +
            "where b.id = :businessId and m.id = :menuId")
    Page<Product> getAllProducts(UUID businessId, UUID menuId, Pageable pageable);
}