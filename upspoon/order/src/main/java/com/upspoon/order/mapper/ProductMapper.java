package com.upspoon.order.mapper;

import com.upspoon.common.dto.Order.ProductDTO;
import com.upspoon.common.mapper.EntityMapper;
import com.upspoon.order.model.Product;
import org.mapstruct.Mapper;

/**
 * @author burak.yesildal
 */


@Mapper(componentModel = "spring", uses = {BusinessMapper.class})
public interface ProductMapper extends EntityMapper<ProductDTO, Product> {

    Product toEntity(ProductDTO productDTO);

    ProductDTO toDto(Product product);

}
