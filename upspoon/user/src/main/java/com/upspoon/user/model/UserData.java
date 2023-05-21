package com.upspoon.user.model;

import com.upspoon.common.model.AbstractAuditBaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "user_data")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@SQLDelete(sql = "UPDATE user_data SET deleted = '1' where id = ?")
@Where(clause = "deleted <> '1' ")
public class UserData extends AbstractAuditBaseEntity {
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "mail_address")
    private String mailAddress;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "order_element_collection", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "order_id")
    private Set<UUID> orderHistory = new HashSet<>();


    private String getFullName() {
        return firstName
                .concat(" ")
                .concat(middleName == null ? "" : middleName.concat(" "))
                .concat(lastName);
    }
}
