package com.upspoon.order.mapper;

import com.upspoon.common.dto.Order.ProductDTO;
import com.upspoon.common.mapper.EntityMapper;
import com.upspoon.order.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author burak.yesildal
 */


@Mapper(componentModel = "spring")
public interface ProductMapper extends EntityMapper<ProductDTO, Product> {
    @Mapping(target = "productCode", expression = "java(java.util.UUID.randomUUID().toString())")
    Product toEntity(ProductDTO productDTO);

    ProductDTO toDto(Product product);

}
