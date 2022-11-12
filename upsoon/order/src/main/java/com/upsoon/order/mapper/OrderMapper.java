package com.upsoon.order.mapper;

import com.upsoon.common.dto.Order.OrderDTO;
import com.upsoon.common.mapper.EntityMapper;
import com.upsoon.order.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * @author burak.yesildal
 */

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderMapper extends EntityMapper<OrderDTO, Order> {

    @Override
    Order toEntity(OrderDTO dto);

    @Override
    OrderDTO toDto(Order entity);
}
