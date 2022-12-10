package com.upspoon.organization.mapper;

import com.upspoon.common.dto.Organization.NewRestaurantUserDTO;
import com.upspoon.common.dto.Organization.UpdateRestaurantUserDTO;
import com.upspoon.common.mapper.EntityMapper;
import com.upspoon.organization.model.RestaurantUser;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * @author burak.yesildal
 */

@Mapper(componentModel = "spring", uses = {}, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface NewRestaurantUserCreateMapper extends EntityMapper<NewRestaurantUserDTO, RestaurantUser> {

    NewRestaurantUserDTO toDto(RestaurantUser restaurantUser);

    RestaurantUser toEntity(NewRestaurantUserDTO newRestaurantUserDTO);

    RestaurantUser updateEntity(@MappingTarget RestaurantUser restaurantUser, UpdateRestaurantUserDTO newRestaurantUserDTO);

}
