package com.upsoon.organization.repository;

import com.upsoon.common.dto.NewOrganizationDTO;
import com.upsoon.common.dto.NewRestaurantUserDTO;
import com.upsoon.organization.model.Organization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, UUID> {
    @Query("select distinct new com.upsoon.common.dto.NewRestaurantUserDTO(ru.id, ru.name, ru.lastName, ru.middleName, ru.phoneNumber, ru.email) " +
            "from  Organization  o join o.restaurantUsers ru " +
            "where o.id = :organizationId")
    Page<NewRestaurantUserDTO> getAllUsers(UUID organizationId, Pageable pageable);

}
