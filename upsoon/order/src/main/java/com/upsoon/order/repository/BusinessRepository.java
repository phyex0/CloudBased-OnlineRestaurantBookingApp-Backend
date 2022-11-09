package com.upsoon.order.repository;


import com.upsoon.common.dto.Order.BusinessDTOForUI;
import com.upsoon.common.enums.BusinessTypes;
import com.upsoon.order.model.Business;
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
public interface BusinessRepository extends JpaRepository<Business, UUID> {

    @Query("select new com.upsoon.common.dto.Order.BusinessDTOForUI(o.id, b.id, o.organizationName, b.businessImage) " +
            "from Business b left join Organization o where b.businessTypes = :businessTypes")
    Page<BusinessDTOForUI> getAllOrganizationByBusinessType(BusinessTypes businessTypes, Pageable pageable);
}