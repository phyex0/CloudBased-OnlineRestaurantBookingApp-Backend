package com.upspoon.order.model;

import com.upspoon.common.model.AbstractAuditBaseEntity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

/**
 * @author burak.yesildal
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

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "menu_id")
    private List<Product> productList;


}
