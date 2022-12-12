package com.upspoon.payment.mapper;

import com.upspoon.common.kafkaTemplateDTO.StockToPayment;
import com.upspoon.common.mapper.EntityMapper;
import com.upspoon.payment.model.PaymentHistory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author burak.yesildal
 */


@Mapper(componentModel = "spring")
public interface PaymentHistoryMapper extends EntityMapper<StockToPayment, PaymentHistory> {

    @Override
    @Mapping(source = "price", target = "amount")
    PaymentHistory toEntity(StockToPayment dto);

    @Override
    @Mapping(source = "amount", target = "price")
    StockToPayment toDto(PaymentHistory entity);
}
