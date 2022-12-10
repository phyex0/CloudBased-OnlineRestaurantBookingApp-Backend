package com.upspoon.order.repository;


import com.upspoon.common.dto.Order.BusinessDTOForUI;
import com.upspoon.common.enums.BusinessTypes;
import com.upspoon.order.model.Business;
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
public interface BusinessRepository extends JpaRepository<Business, UUID> {

    @Query("select new com.upspoon.common.dto.Order.BusinessDTOForUI(o.id, b.id, o.organizationName, b.businessImage) " +
            "from Business b left join Organization o where b.businessTypes = :businessTypes")
    Page<BusinessDTOForUI> getAllOrganizationByBusinessType(BusinessTypes businessTypes, Pageable pageable);
}
