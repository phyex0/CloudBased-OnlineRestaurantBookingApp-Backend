package com.upspoon.booking.repository;

import com.upspoon.booking.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author burak.yesildal
 */


@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {
}
