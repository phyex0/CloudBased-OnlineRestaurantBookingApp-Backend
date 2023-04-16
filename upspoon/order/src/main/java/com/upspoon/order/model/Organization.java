package com.upspoon.order.model;

import com.upspoon.common.enums.BusinessTypes;
import com.upspoon.common.model.AbstractAuditBaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.List;
import java.util.UUID;

/**
 * @author burak.yesildal
 */

@Entity
@Table(name = "organization")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@SQLDelete(sql = "UPDATE organization SET deleted = '1' where id = ?")
@Where(clause = "deleted <> '1' ")
public class Organization extends AbstractAuditBaseEntity {

    @Column(name = "exact_organization_id", nullable = false)
    private UUID exactOrganizationId;

    @Column(name = "organization_name", nullable = false)
    private String organizationName;

    @Enumerated(EnumType.STRING)
    @Column(name = "business_type", nullable = false)
    private BusinessTypes businessTypes;

    @Column(name = "organization_image")
    private String organizationImage;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "organization_id")
    private List<Menu> menuList;


}



