package com.upsoon.order.repository;

import com.upsoon.order.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * @author Halit Burak Yeşildal
 */

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    @Query("select p from Business b left join  b.menuList m left  join  m.productList p " +
            "where b.id = :businessId and m.id = :menuId")
    Page<Product> getAllProducts(UUID businessId, UUID menuId, Pageable pageable);

    Boolean existsAllByIdIn(List<UUID> idList);

    @Query("select SUM(p.price) from Product p where p.id in (:idList)")
    Double getTotalAmount(List<UUID> idList);

    Product findProductByProductCode(String productCode);

    List<Product> findAllByIdIn(List<UUID> idList);
}
