package com.upspoon.stock.model;

import com.upspoon.common.model.AbstractAuditBaseEntity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "stock_transaction")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@SQLDelete(sql = "UPDATE stock_transaction SET deleted = '1' where id = ?")
@Where(clause = "deleted <> '1' ")
public class StockTransaction extends AbstractAuditBaseEntity {

    @Column(name = "order_id", nullable = false)
    private UUID orderId;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "stock_transaction_id")
    private List<StockTransactionProductCount> stockTransactionProductCounts;

}
