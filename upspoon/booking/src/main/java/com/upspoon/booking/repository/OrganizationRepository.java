package com.upspoon.booking.repository;

import com.upspoon.booking.model.Book;
import com.upspoon.booking.model.BookDetails;
import com.upspoon.booking.model.Organization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.UUID;

/**
 * @author burak.yesildal
 */


@Repository
public interface OrganizationRepository extends JpaRepository<Organization, UUID> {

    Organization findByExactOrganizationId(UUID organizationId);

    @Query("select b from  Organization  o left join o.bookList b " +
            "where  o.exactOrganizationId = :businessId and b.date >= :date " +
            "and  (coalesce(:selectedDate) is null or b.date = :selectedDate) ")
    Page<Book> getBooks(UUID businessId, Date date, Date selectedDate, Pageable pageable);

    @Query("select bd from Organization  o left join o.bookList b left join b.bookDetailsList bd " +
            "where o.exactOrganizationId = :businessId and b.id = :bookId")
    Page<BookDetails> getBookDetails(UUID businessId, UUID bookId, Pageable pageable);
}
