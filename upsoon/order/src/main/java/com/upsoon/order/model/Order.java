package com.upsoon.order.model;


import com.upsoon.common.enums.OrderStatus;
import com.upsoon.common.model.AbstractAuditBaseEntity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author burak.yesildal
 */

@Entity
@Table(name = "order")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@SQLDelete(sql = "UPDATE order SET deleted = '1' where id = ?")
@Where(clause = "deleted <> '1' ")
public class Order extends AbstractAuditBaseEntity {

    private UUID userId;

    @ElementCollection
    private List<UUID> productId;

    private OrderStatus orderStatus;

    private String orderNote;

    private Date orderDate;

    private Double amount;


}
