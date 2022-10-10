package com.upsoon.organization.model;


import com.upsoon.common.enums.PackageService;
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
@Table(name = "organization")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE organization SET deleted = '1' where id = ?")
@Where(clause = "deleted <> '1' ")
public class Organization extends AbstractAuditBaseEntity implements Serializable {

    @Column(name = "organization-name", nullable = false, length = 255)
    private String organizationName;

    @Enumerated(EnumType.STRING)
    @Column(name = "package-service-type")
    private PackageService packageService;

    @ManyToOne
    private User user;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Restaurant> companies;

}
