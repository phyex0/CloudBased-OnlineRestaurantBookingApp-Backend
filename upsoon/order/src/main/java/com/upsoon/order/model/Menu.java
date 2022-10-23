package com.upsoon.order.model;

import com.upsoon.common.model.AbstractAuditBaseEntity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

/**
 * @author Halit Burak Yeşildal
 */

@Entity
@Table(name = "menu")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@SQLDelete(sql = "UPDATE menu SET deleted = '1' where id = ?")
@Where(clause = "deleted <> '1' ")
public class Menu extends AbstractAuditBaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Product> productList;


}
