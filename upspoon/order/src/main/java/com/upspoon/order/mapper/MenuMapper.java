package com.upspoon.order.mapper;

import com.upspoon.common.dto.Order.MenuDTO;
import com.upspoon.common.dto.Order.UpdateMenuDTO;
import com.upspoon.common.mapper.EntityMapper;
import com.upspoon.order.model.Menu;
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
