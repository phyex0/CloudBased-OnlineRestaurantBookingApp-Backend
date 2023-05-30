package com.upspoon.organization.repository;

import com.upspoon.common.dto.Organization.NewOrganizationDTO;
import com.upspoon.organization.model.RestaurantUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

/**
 * @author burak.yesildal
 */

public interface RestaurantUserRepository extends JpaRepository<RestaurantUser, UUID> {
    @Query(value = "select distinct new com.upspoon.common.dto.Organization.NewOrganizationDTO(o.id, o.organizationName, o.packageService, o.fullAddress,o.businessType) " +
            "from  RestaurantUser  ru left join ru.organizations o " +
            "where ru.id = :restaurantUserId and ru.deleted is false and o.deleted is false ", nativeQuery = true)
    Page<NewOrganizationDTO> getAllOrganizations(UUID restaurantUserId, Pageable pageable);

    Optional<RestaurantUser> findRestaurantUserByEmail(String mail);
}
