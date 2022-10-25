package com.upsoon.order.mapper;

import com.upsoon.common.dto.Order.BusinessDTO;
import com.upsoon.common.mapper.EntityMapper;
import com.upsoon.order.model.Business;
import org.mapstruct.Mapper;

/**
 * @author Halit Burak Yeşildal
 */


@Mapper(componentModel = "spring", uses = {MenuMapper.class})
public interface BusinessMapper extends EntityMapper<BusinessDTO, Business> {

    Business toEntity(BusinessDTO businessDTO);

    BusinessDTO toDto(Business business);

}
