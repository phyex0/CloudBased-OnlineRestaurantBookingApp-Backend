package com.upsoon.organization.mapper;

import com.upsoon.common.dto.NewRestaurantUserDTO;
import com.upsoon.common.mapper.EntityMapper;
import com.upsoon.organization.model.RestaurantUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface NewRestaurantUserCreateMapper extends EntityMapper<NewRestaurantUserDTO, RestaurantUser> {

    NewRestaurantUserDTO toDto(RestaurantUser restaurantUser);

    RestaurantUser toEntity(NewRestaurantUserDTO newRestaurantUserDTO);
}
