package com.upsoon.organization.repository;

import com.upsoon.common.dto.Organization.NewRestaurantUserDTO;
import com.upsoon.organization.model.Organization;
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
public interface OrganizationRepository extends JpaRepository<Organization, UUID> {
    @Query("select distinct new com.upsoon.common.dto.Organization.NewRestaurantUserDTO(ru.id, ru.name, ru.lastName, ru.middleName, ru.phoneNumber, ru.email) " +
            "from  Organization  o join o.restaurantUsers ru " +
            "where o.id = :organizationId and o.deleted is false and ru.deleted is false ")
    Page<NewRestaurantUserDTO> getAllUsers(UUID organizationId, Pageable pageable);

    @Query("select o  from Organization o left join o.restaurantUsers ru " +
            "where ru.id = :userId")
    List<Organization> getAllOrganizationsByUserId(UUID userId);

}
