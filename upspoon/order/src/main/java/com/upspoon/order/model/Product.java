package com.upspoon.order.model;

import com.upspoon.common.model.AbstractAuditBaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;


/**
 * @author burak.yesildal
 */
@Entity
@Table(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@SQLDelete(sql = "UPDATE product SET deleted = '1' where id = ?")
@Where(clause = "deleted <> '1' ")
public class Product extends AbstractAuditBaseEntity {

    @Column(name = "product_code")
    private String productCode;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "product_image")
    private String productImage;

}
