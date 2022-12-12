package com.upspoon.stock.mapper;

import com.upspoon.common.kafkaTemplateDTO.OrderToStock;
import com.upspoon.common.mapper.EntityMapper;
import com.upspoon.stock.model.StockTransaction;
import com.upspoon.stock.model.StockTransactionProductCount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author burak.yesildal
 */

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderToStockMapper extends EntityMapper<OrderToStock, StockTransaction> {

    @Override
    @Mapping(target = "stockTransactionProductCounts", expression = "java(mapToListExtractor(dto))")
    StockTransaction toEntity(OrderToStock dto);

    @Override
    OrderToStock toDto(StockTransaction entity);

    default List<StockTransactionProductCount> mapToListExtractor(OrderToStock orderToStock) {
        List<StockTransactionProductCount> list = new ArrayList<>();

        orderToStock.getProductMap().keySet().forEach(key -> {
            StockTransactionProductCount stockTransactionProductCount = new StockTransactionProductCount(key, orderToStock.getProductMap().get(key));
            list.add(stockTransactionProductCount);
        });

        return list;


    }
}
