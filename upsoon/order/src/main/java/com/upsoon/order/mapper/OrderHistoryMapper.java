package com.upsoon.order.mapper;

import com.upsoon.common.dto.Order.OrderHistoryDTO;
import com.upsoon.common.mapper.EntityMapper;
import com.upsoon.order.model.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderHistoryMapper extends EntityMapper<OrderHistoryDTO, Order> {

    @Override
    Order toEntity(OrderHistoryDTO dto);

    @Override
    OrderHistoryDTO toDto(Order entity);


}
