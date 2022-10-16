package com.upsoon.organization.repository;

import com.upsoon.common.dto.NewOrganizationDTO;
import com.upsoon.organization.model.RestaurantUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface RestaurantUserRepository extends JpaRepository<RestaurantUser, UUID> {
    @Query("select distinct new com.upsoon.common.dto.NewOrganizationDTO(o.id, o.organizationName, o.packageService, o.fullAddress) " +
            "from  RestaurantUser  ru left join ru.organizations o " +
            "where ru.id = :restaurantUserId and ru.deleted is false and o.deleted is false ")
    Page<NewOrganizationDTO> getAllOrganizations(UUID restaurantUserId, Pageable pageable);


}
