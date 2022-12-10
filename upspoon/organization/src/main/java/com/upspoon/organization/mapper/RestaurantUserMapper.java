package com.upspoon.organization.mapper;

import com.upspoon.common.dto.Organization.RestaurantUserDTO;
import com.upspoon.common.mapper.EntityMapper;
import com.upspoon.organization.model.RestaurantUser;
import org.mapstruct.Mapper;

/**
 * @author burak.yesildal
 */

@Mapper(componentModel = "spring", uses = {OrganizationMapper.class})
public interface RestaurantUserMapper extends EntityMapper<RestaurantUserDTO, RestaurantUser> {


    RestaurantUserDTO toDto(RestaurantUser restaurantUser);

    RestaurantUser toEntity(RestaurantUserDTO restaurantUserDTO);

}
