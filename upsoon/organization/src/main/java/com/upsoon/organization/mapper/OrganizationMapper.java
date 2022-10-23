package com.upsoon.organization.mapper;


import com.upsoon.common.dto.OrganizationDTO;
import com.upsoon.common.mapper.EntityMapper;
import com.upsoon.organization.model.Organization;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author Halit Burak Yeşildal
 */

@Mapper(componentModel = "spring", uses = {RestaurantUserMapper.class})
public interface OrganizationMapper extends EntityMapper<OrganizationDTO, Organization> {


    @Mapping(target = "restaurantUsers", ignore = true)
    Organization toEntity(OrganizationDTO dto);

    @Mapping(target = "restaurantUsers", ignore = true)
    OrganizationDTO toDto(Organization entity);
}
