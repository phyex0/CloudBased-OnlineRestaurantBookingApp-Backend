package com.upspoon.booking.model;

import com.upspoon.common.model.AbstractAuditBaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

/**
 * @author burak.yesildal
 */

@Entity
@Table(name = "book_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@SQLDelete(sql = "UPDATE book_details SET deleted = '1' where id = ?")
@Where(clause = "deleted <> '1' ")
public class BookDetails extends AbstractAuditBaseEntity {
    @Column(name = "full_name")
    private String fullName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "number_of_people")
    private Integer numberOfPeople;

    @Column(name = "book_date")
    private LocalDate bookDate;

    @Column(name = "table_number")
    private Integer tableNumber;

    @Column(name = "user_id")
    private UUID userId;
}
