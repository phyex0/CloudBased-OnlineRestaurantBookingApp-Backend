package com.upsoon.order.repository;

import com.upsoon.order.model.Business;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @author Halit Burak Yeşildal
 */


public interface BusinessRepository extends JpaRepository<Business, UUID>{
}
