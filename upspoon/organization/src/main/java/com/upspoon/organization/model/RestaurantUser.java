package com.upspoon.organization.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "restaurant_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
@SQLDelete(sql = "UPDATE restaurant_user SET deleted = '1' where id = ?")
@Where(clause = "deleted <> '1' ")
public class RestaurantUser extends AbstractAuditBaseEntity implements Serializable {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "email", nullable = false)
    private String email;


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "restaurantUsers")
    @JsonIgnore
    private Set<Organization> organizations = new HashSet<>();
}
