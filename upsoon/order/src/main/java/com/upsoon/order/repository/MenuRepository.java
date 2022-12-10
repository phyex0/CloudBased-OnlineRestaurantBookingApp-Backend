package com.upsoon.order.repository;

import com.upsoon.common.dto.Order.MenuDTO;
import com.upsoon.order.model.Menu;
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
public interface MenuRepository extends JpaRepository<Menu, UUID> {

    @Query("select new com.upsoon.common.dto.Order.MenuDTO(m.id, m.name) from Menu m where m.id in (:idList) ")
    Page<MenuDTO> getMenuByIdList(List<UUID> idList, Pageable pageable);

}
