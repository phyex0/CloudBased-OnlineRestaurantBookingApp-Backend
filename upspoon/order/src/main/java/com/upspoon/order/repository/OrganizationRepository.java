package com.upspoon.order.repository;

import com.upspoon.common.dto.Order.BusinessDTOForUI;
import com.upspoon.common.enums.BusinessTypes;
import com.upspoon.order.model.Organization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author burak.yesildal
 */

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, UUID> {
    Organization findOrganizationByExactOrganizationId(UUID organizationId);

    @Query("select new com.upspoon.common.dto.Order.BusinessDTOForUI(o.exactOrganizationId, o.organizationName, o.organizationImage, o.businessTypes) " +
            "from Organization  o where o.businessTypes = :businessTypes order by  o.organizationName asc")
    Page<BusinessDTOForUI> getAllOrganizationsByBusinessType(BusinessTypes businessTypes, Pageable pageable);

}
