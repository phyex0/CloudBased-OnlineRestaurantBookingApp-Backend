package com.upspoon.booking.model;

import com.upspoon.common.model.AbstractAuditBaseEntity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

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
    private Date bookDate;

    @Column(name = "table_number")
    private Integer tableNumber;
}
