package com.upspoon.order.repository;


import com.upspoon.order.model.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author burak.yesildal
 */

@Repository
public interface BusinessRepository extends JpaRepository<Business, UUID> {

}
