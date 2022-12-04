package com.upsoon.stock.model;


import com.upsoon.common.model.AbstractAuditBaseEntity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.tuple.entity.AbstractEntityBasedAttribute;

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

    private UUID productId;

    private Long count;


}
