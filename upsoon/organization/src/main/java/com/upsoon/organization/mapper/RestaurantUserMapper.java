package com.upsoon.organization.mapper;

import com.upsoon.common.dto.RestaurantUserDTO;
import com.upsoon.common.mapper.EntityMapper;
import com.upsoon.organization.model.RestaurantUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {OrganizationMapper.class})
public interface RestaurantUserMapper extends EntityMapper<RestaurantUserDTO, RestaurantUser> {


    RestaurantUserDTO toDto(RestaurantUser restaurantUser);

    RestaurantUser toEntity(RestaurantUserDTO restaurantUserDTO);

}