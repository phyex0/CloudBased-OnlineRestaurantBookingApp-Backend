package com.upspoon.organization.repository;

import com.upspoon.common.dto.Organization.NewRestaurantUserDTO;
import com.upspoon.organization.model.Organization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * @author burak.yesildal
 */

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, UUID> {
    @Query(value = "select distinct new com.upspoon.common.dto.Organization.NewRestaurantUserDTO(ru.id, ru.name, ru.lastName, ru.middleName, ru.phoneNumber, ru.email) " +
            "from  Organization  o join o.restaurantUsers ru " +
            "where o.id = :organizationId and o.deleted is false and ru.deleted is false ",nativeQuery = true)
    Page<NewRestaurantUserDTO> getAllUsers(UUID organizationId, Pageable pageable);

    @Query("select o  from Organization o left join o.restaurantUsers ru " +
            "where ru.id = :userId")
    List<Organization> getAllOrganizationsByUserId(UUID userId);

}
