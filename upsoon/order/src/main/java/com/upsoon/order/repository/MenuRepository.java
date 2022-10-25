package com.upsoon.order.repository;

import com.upsoon.order.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @author Halit Burak Yeşildal
 */


public interface MenuRepository extends JpaRepository<Menu, UUID> {
}
