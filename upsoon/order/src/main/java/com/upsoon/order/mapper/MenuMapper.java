package com.upsoon.order.mapper;

import com.upsoon.common.dto.Order.MenuDTO;
import com.upsoon.common.dto.Order.UpdateMenuDTO;
import com.upsoon.common.mapper.EntityMapper;
import com.upsoon.order.model.Menu;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * @author burak.yesildal
 */

@Mapper(componentModel = "spring", uses = {ProductMapper.class}, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MenuMapper extends EntityMapper<MenuDTO, Menu> {

    Menu toEntity(MenuDTO menuDTO);

    MenuDTO toDto(Menu menu);

    Menu updateEntity(@MappingTarget Menu menu, UpdateMenuDTO updateMenuDTO);

}
