package com.upsoon.order.mapper;

import com.upsoon.common.kafkaTemplateDTO.OrderToStock;
import com.upsoon.common.mapper.EntityMapper;
import com.upsoon.order.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderToStockMapper extends EntityMapper<OrderToStock, Order> {


    Order toEntity(OrderToStock dto);

    @Mapping(target = "productMap", expression = "java(mapCreator(entity))")
    OrderToStock toDto(Order entity);


    default Map<UUID, Integer> mapCreator(Order order) {
        Map<UUID, Integer> map = new HashMap<>();
        order.getProductId().forEach(product -> map.put(product, map.getOrDefault(product, 0) + 1));
        return map;
    }
}
