package com.upspoon.order.mapper;

import com.upspoon.common.dto.Order.OrderDTO;
import com.upspoon.common.mapper.EntityMapper;
import com.upspoon.order.model.Order;
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
