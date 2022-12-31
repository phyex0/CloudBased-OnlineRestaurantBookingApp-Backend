package com.upspoon.booking.mapper;

import com.upspoon.booking.model.BookDetails;
import com.upspoon.common.dto.Booking.CreateBookDetailDTO;
import com.upspoon.common.mapper.EntityMapper;
import org.mapstruct.Mapper;

/**
 * @author burak.yesildal
 */


@Mapper(componentModel = "spring")
public interface BookDetailsDTOToBookDetailsMapper extends EntityMapper<CreateBookDetailDTO, BookDetails> {

    BookDetails toEntity(CreateBookDetailDTO dto);

    CreateBookDetailDTO toDto(BookDetails entity);
}
