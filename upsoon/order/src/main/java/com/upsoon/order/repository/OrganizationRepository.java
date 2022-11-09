package com.upsoon.order.repository;

import com.upsoon.common.dto.Order.OrganizationDTO;
import com.upsoon.common.enums.BusinessTypes;
import com.upsoon.order.model.Organization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author Halit Burak Yeşildal
 */

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, UUID> {
    Organization findOrganizationByExactOrganizationId(UUID organizationId);



}
