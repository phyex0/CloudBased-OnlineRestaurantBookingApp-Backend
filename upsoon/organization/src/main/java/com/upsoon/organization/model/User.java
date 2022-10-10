package com.upsoon.organization.model;


import com.upsoon.common.model.AbstractAuditBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE user SET deleted = '1' where id = ?")
@Where(clause = "deleted <> '1' ")
public class User extends AbstractAuditBaseEntity implements Serializable {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "middle_name", nullable = false)
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "email", nullable = false)
    private String email;
}
