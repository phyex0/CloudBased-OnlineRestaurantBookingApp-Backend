package com.upspoon.order.model;


import com.upspoon.common.enums.OrderStatus;
import com.upspoon.common.model.AbstractAuditBaseEntity;
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
@Table(name = "order_history")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@SQLDelete(sql = "UPDATE order_history SET deleted = '1' where id = ?")
@Where(clause = "deleted <> '1' ")
public class Order extends AbstractAuditBaseEntity {

    //TODO: business id is not stored! Also if a user want to buy products from multiple business what to do?

    @Column(name = "user_id")
    private UUID userId;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "order_element_collection", joinColumns = @JoinColumn(name = "order_id"))
    @Column(name = "product_id")
    private List<UUID> productId;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", nullable = false)
    private OrderStatus orderStatus;

    @Column(name = "order_note")
    private String orderNote;

    @Column(name = "order_date", nullable = false)
    private Date orderDate = new Date();

    @Column(name = "total_amount", nullable = false)
    private Double totalAmount;


}
