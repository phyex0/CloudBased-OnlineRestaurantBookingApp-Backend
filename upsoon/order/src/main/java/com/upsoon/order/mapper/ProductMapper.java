package com.upsoon.order.mapper;

import com.upsoon.common.dto.Order.ProductDTO;
import com.upsoon.common.mapper.EntityMapper;
import com.upsoon.order.model.Product;
import org.mapstruct.Mapper;

/**
 * @author burak.yesildal
 */


@Mapper(componentModel = "spring", uses = {BusinessMapper.class})
public interface ProductMapper extends EntityMapper<ProductDTO, Product> {

    Product toEntity(ProductDTO productDTO);

    ProductDTO toDto(Product product);

}
