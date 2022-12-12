package com.upspoon.payment.repository;

import com.upspoon.payment.model.PaymentHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author burak.yesildal
 */
@Repository
public interface PaymentHistoryRepository extends JpaRepository<PaymentHistory, UUID> {

    Boolean existsByUserId(UUID userID);

}
