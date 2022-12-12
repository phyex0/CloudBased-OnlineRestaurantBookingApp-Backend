package com.upspoon.payment.model;

import com.upspoon.common.enums.PaymentStatus;
import com.upspoon.common.model.AbstractAuditBaseEntity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * @author burak.yesildal
 */

@Entity
@Table(name = "payment_history")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@SQLDelete(sql = "UPDATE payment_history SET deleted = '1' where id = ?")
@Where(clause = "deleted <> '1' ")
public class PaymentHistory extends AbstractAuditBaseEntity {
    @Column(name = "order_id", nullable = false)
    private UUID orderId;
    @Column(name = "user_id", nullable = false)
    private UUID userId;
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status")
    private PaymentStatus paymentStatus;

    @Column(name = "amount", nullable = false)
    private Double amount;
    @Column(name = "payment_date", nullable = false)
    private Date paymentDate = new Date();

}
