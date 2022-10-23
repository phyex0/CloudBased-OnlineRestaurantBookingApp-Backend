package com.upsoon.order.model;

import com.upsoon.common.enums.BusinessTypes;
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
@Table(name = "business")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@SQLDelete(sql = "UPDATE business SET deleted = '1' where id = ?")
@Where(clause = "deleted <> '1' ")
public class Business extends AbstractAuditBaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "business_type", nullable = false)
    private BusinessTypes businessTypes;

    @Column(name = "business_image")
    private String businessImage;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private List<Menu> menuList;

}
