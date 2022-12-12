package com.upspoon.stock.model;


import com.upspoon.common.model.AbstractAuditBaseEntity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "stock")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@SQLDelete(sql = "UPDATE stock SET deleted = '1' where id = ?")
@Where(clause = "deleted <> '1' ")
public class Stock extends AbstractAuditBaseEntity {

    @Column(name = "product_id", nullable = false)
    private UUID productId;
    @Column(name = "count")
    private Long count;


}
