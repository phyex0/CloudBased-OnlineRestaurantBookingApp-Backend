package com.upsoon.organization.model;


import com.upsoon.common.enums.PackageService;
import com.upsoon.common.model.AbstractAuditBaseEntity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
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

    @Column(name = "is_market")
    private boolean isMarket;

    @Column(name = "is_restaurant")
    private boolean isRestaurant;

    @Column(name = "is_booking")
    private boolean isBooking;

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
