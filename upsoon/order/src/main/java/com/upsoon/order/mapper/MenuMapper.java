package com.upsoon.order.mapper;

import com.upsoon.common.dto.Order.MenuDTO;
import com.upsoon.common.mapper.EntityMapper;
import com.upsoon.order.model.Menu;
import org.mapstruct.Mapper;

/**
 * @author Halit Burak Yeşildal
 */

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface MenuMapper extends EntityMapper<MenuDTO, Menu> {

    Menu toEntity(MenuDTO menuDTO);

    MenuDTO toDto(Menu menu);
}
