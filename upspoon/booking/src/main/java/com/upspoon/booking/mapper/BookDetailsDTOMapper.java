package com.upspoon.booking.mapper;

import com.upspoon.booking.model.BookDetails;
import com.upspoon.common.dto.Booking.BookDetailDTO;
import com.upspoon.common.mapper.EntityMapper;
import org.mapstruct.Mapper;

/**
 * @author burak.yesildal
 */
@Mapper(componentModel = "spring")
public interface BookDetailsDTOMapper extends EntityMapper<BookDetailDTO, BookDetails> {
    @Override
    BookDetails toEntity(BookDetailDTO dto);

    @Override
    BookDetailDTO toDto(BookDetails entity);
}
