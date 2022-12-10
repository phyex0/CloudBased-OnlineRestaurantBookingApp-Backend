package com.upsoon.stock.mapper;

import com.upsoon.common.kafkaTemplateDTO.OrderToStock;
import com.upsoon.common.kafkaTemplateDTO.StockToPayment;
import com.upsoon.common.mapper.EntityMapper;
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
