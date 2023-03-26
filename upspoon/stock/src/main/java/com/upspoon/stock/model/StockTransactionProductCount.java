package com.upspoon.stock.model;

import com.upspoon.common.model.AbstractAuditBaseEntity;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

/**
 * @author burak.yesildal
 */

@Entity
@Table(name = "stock_transaction_product_count")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@SQLDelete(sql = "UPDATE stock_transaction_product_count SET deleted = '1' where id = ?")
@Where(clause = "deleted <> '1' ")
public class StockTransactionProductCount extends AbstractAuditBaseEntity {
    @Column(name = "product_id", nullable = false)
    private UUID productId;
    @Column(name = "count")
    @Min(0)
    private Long count;

}
