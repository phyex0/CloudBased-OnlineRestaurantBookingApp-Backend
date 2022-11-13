package com.upsoon.stock.model;

import com.upsoon.common.model.AbstractAuditBaseEntity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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

    private UUID orderId;

    @OneToMany(fetch = FetchType.LAZY)
    private List<ProductCount> productCounts;

}
