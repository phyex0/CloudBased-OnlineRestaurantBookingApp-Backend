package com.upsoon.organization.mapper;

import com.upsoon.common.dto.Organization.NewRestaurantUserDTO;
import com.upsoon.common.dto.Organization.UpdateRestaurantUserDTO;
import com.upsoon.common.mapper.EntityMapper;
import com.upsoon.organization.model.RestaurantUser;
import org.mapstruct.*;

/**
 * @author Halit Burak Yeşildal
 */

@Mapper(componentModel = "spring", uses = {}, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface NewRestaurantUserCreateMapper extends EntityMapper<NewRestaurantUserDTO, RestaurantUser> {

    NewRestaurantUserDTO toDto(RestaurantUser restaurantUser);

    RestaurantUser toEntity(NewRestaurantUserDTO newRestaurantUserDTO);

    RestaurantUser updateEntity(@MappingTarget RestaurantUser restaurantUser, UpdateRestaurantUserDTO newRestaurantUserDTO);

}
