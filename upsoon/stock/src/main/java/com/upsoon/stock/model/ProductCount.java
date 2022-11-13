package com.upsoon.stock.model;

import com.upsoon.common.model.AbstractAuditBaseEntity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

/**
 * @author Halit Burak Yeşildal
 */

@Entity
@Table(name = "product_count")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@SQLDelete(sql = "UPDATE product_count SET deleted = '1' where id = ?")
@Where(clause = "deleted <> '1' ")
public class ProductCount extends AbstractAuditBaseEntity {

    private UUID productId;

    private Long count;

}
