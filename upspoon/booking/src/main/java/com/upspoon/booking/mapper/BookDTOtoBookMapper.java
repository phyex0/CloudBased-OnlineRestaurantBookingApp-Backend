package com.upspoon.booking.mapper;

import com.upspoon.booking.model.Book;
import com.upspoon.common.dto.Booking.BookDTO;
import com.upspoon.common.mapper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author burak.yesildal
 */
@Mapper(componentModel = "spring")
public interface BookDTOtoBookMapper extends EntityMapper<BookDTO, Book> {

    @Override
    Book toEntity(BookDTO dto);

    @Override
    @Mapping(target = "reservationCount", expression = "java(entity.getBookDetailsList().size())")
    BookDTO toDto(Book entity);


}
