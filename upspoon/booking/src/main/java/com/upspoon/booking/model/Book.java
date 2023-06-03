package com.upspoon.booking.model;

import com.upspoon.common.model.AbstractAuditBaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDate;
import java.util.List;

/**
 * @author burak.yesildal
 */

@Entity
@Table(name = "book")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@SQLDelete(sql = "UPDATE book SET deleted = '1' where id = ?")
@Where(clause = "deleted <> '1' ")
public class Book extends AbstractAuditBaseEntity {
    @Column(name = "book_date")
    private LocalDate date;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "book_id")
    private List<BookDetails> bookDetailsList;

}
