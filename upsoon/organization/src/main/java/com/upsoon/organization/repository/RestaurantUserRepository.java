package com.upsoon.organization.repository;

import com.upsoon.organization.model.RestaurantUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RestaurantUserRepository extends JpaRepository<RestaurantUser, UUID> {
}
