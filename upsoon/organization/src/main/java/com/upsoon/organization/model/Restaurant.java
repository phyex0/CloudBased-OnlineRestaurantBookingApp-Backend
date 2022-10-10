package com.upsoon.organization.model;


import com.upsoon.common.model.AbstractAuditBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "restaurant")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE restaurant SET deleted = '1' where id = ?")
@Where(clause = "deleted <> '1' ")
public class Restaurant extends AbstractAuditBaseEntity implements Serializable {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "distinct", nullable = false)
    private String distinct;

    @Column(name = "full_address", nullable = false)
    private String fullAddress;

    @Column(name = "zip_code")
    private String zipCode;

    @ManyToMany
    private List<User> user;

    @ManyToOne(cascade = {CascadeType.MERGE})
    private Organization organization;


}
