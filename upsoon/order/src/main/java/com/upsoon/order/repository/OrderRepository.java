package com.upsoon.order.repository;

import com.upsoon.order.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author burak.yesildal
 */

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

    Page<Order> getAllByUserId(UUID userId, Pageable pageable);
}
