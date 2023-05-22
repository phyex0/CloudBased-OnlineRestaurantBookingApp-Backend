package com.upspoon.organization.model;


import com.upspoon.common.enums.BusinessTypes;
import com.upspoon.common.enums.PackageService;
import com.upspoon.common.model.AbstractAuditBaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author burak.yesildal
 */


@Entity
@Table(name = "organization")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = {"restaurantUsers",/*"childOrganizations"*/})
@ToString(callSuper = true, exclude = "parentOrganization")
@SQLDelete(sql = "UPDATE organization SET deleted = '1' where id = ?")
@Where(clause = "deleted <> '1' ")
public class
Organization extends AbstractAuditBaseEntity implements Serializable {

    @Column(name = "organization_name", nullable = false)
    private String organizationName;

    @Enumerated(EnumType.STRING)
    @Column(name = "package_service_type")
    private PackageService packageService;

    @Enumerated(EnumType.STRING)
    @Column(name = "business_type")
    private BusinessTypes businessType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id", name = "parent_organization")
    private Organization parentOrganization;

    @Column(name = "full_address", nullable = false)
    private String fullAddress;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "organization_user",
            joinColumns = @JoinColumn(name = "organization_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private Set<RestaurantUser> restaurantUsers = new HashSet<>();
}
