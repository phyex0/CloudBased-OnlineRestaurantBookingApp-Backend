package com.upspoon.order.mapper;

import com.upspoon.common.dto.Order.OrderHistoryDTO;
import com.upspoon.common.mapper.EntityMapper;
import com.upspoon.order.model.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderHistoryMapper extends EntityMapper<OrderHistoryDTO, Order> {

    @Override
    Order toEntity(OrderHistoryDTO dto);

    @Override
    OrderHistoryDTO toDto(Order entity);


}
