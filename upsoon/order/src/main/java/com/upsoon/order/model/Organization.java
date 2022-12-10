package com.upsoon.order.model;

import com.upsoon.common.enums.BusinessTypes;
import com.upsoon.common.model.AbstractAuditBaseEntity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
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

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "organization_m_id")
    private Business market;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "organization_r_id")
    private Business restaurant;


    public Business getBusiness(BusinessTypes businessTypes) {
        if (businessTypes.equals(BusinessTypes.MARKET))
            return this.market;
        return this.restaurant;
    }


}



