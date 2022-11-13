package com.upsoon.stock.mapper;

import com.upsoon.common.kafkaTemplateDTO.OrderToStock;
import com.upsoon.common.mapper.EntityMapper;
import com.upsoon.stock.model.ProductCount;
import com.upsoon.stock.model.StockTransaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Halit Burak Yeşildal
 */

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderToStockMapper extends EntityMapper<OrderToStock, StockTransaction> {

    @Override
    @Mapping(target = "productCounts", expression = "java(mapToListExtractor(dto))")
    StockTransaction toEntity(OrderToStock dto);

    @Override
    OrderToStock toDto(StockTransaction entity);

    default List<ProductCount> mapToListExtractor(OrderToStock orderToStock) {
        List<ProductCount> list = new ArrayList<>();

        orderToStock.getProductMap().keySet().forEach(key -> {
            ProductCount productCount = new ProductCount(key, orderToStock.getProductMap().get(key));
            list.add(productCount);
        });

        return list;


    }
}
