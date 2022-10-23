package com.upsoon.order.model;

import com.upsoon.common.enums.PackageService;
import com.upsoon.common.model.AbstractAuditBaseEntity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * @author Halit Burak Yeşildal
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


    @Column(name = "organization_name", nullable = false)
    private String organizationName;

    @Enumerated(EnumType.STRING)
    @Column(name = "package_service_type")
    private PackageService packageService;

    @Column(name = "full_address", nullable = false)
    private String fullAddress;

//    @Column(name = "user_id_list", nullable = false)
//    private List<UUID> userIdList;

    @OneToOne(fetch = FetchType.LAZY)
    private Business market;

    @OneToOne(fetch = FetchType.LAZY)
    private Business restaurant;



}



