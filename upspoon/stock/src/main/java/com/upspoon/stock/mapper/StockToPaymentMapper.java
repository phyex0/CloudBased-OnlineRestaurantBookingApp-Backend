package com.upspoon.stock.mapper;

import com.upspoon.common.kafkaTemplateDTO.OrderToStock;
import com.upspoon.common.kafkaTemplateDTO.StockToPayment;
import com.upspoon.common.mapper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * @author burak.yesildal
 */

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface StockToPaymentMapper extends EntityMapper<OrderToStock, StockToPayment> {

    @Override
    StockToPayment toEntity(OrderToStock dto);

    @Override
    OrderToStock toDto(StockToPayment entity);
}
