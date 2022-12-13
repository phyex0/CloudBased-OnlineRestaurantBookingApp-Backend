package com.upspoon.order.repository;

import com.upspoon.common.dto.Order.BusinessDTOForUI;
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

    @Query("select new com.upspoon.common.dto.Order.BusinessDTOForUI(o.id, b.id, o.organizationName, b.businessImage) " +
            "from Organization o left join o.market b where b.id is not null ")
    Page<BusinessDTOForUI> getAllMarketOrganizationByBusinessType(Pageable pageable);


    @Query("select new com.upspoon.common.dto.Order.BusinessDTOForUI(o.id, b.id, o.organizationName, b.businessImage) " +
            "from Organization o left join o.restaurant b where b.id is not null ")
    Page<BusinessDTOForUI> getAllRestaurantOrganizationByBusinessType(Pageable pageable);


}
