package com.upspoon.order.mapper;

import com.upspoon.common.dto.Order.BusinessDTO;
import com.upspoon.common.mapper.EntityMapper;
import com.upspoon.order.model.Business;
import org.mapstruct.Mapper;

/**
 * @author burak.yesildal
 */


@Mapper(componentModel = "spring", uses = {MenuMapper.class})
public interface BusinessMapper extends EntityMapper<BusinessDTO, Business> {

    Business toEntity(BusinessDTO businessDTO);

    BusinessDTO toDto(Business business);

}
