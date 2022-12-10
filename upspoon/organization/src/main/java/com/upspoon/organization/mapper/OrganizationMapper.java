package com.upspoon.organization.mapper;


import com.upspoon.common.dto.Organization.OrganizationDTO;
import com.upspoon.common.mapper.EntityMapper;
import com.upspoon.organization.model.Organization;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author burak.yesildal
 */

@Mapper(componentModel = "spring", uses = {RestaurantUserMapper.class})
public interface OrganizationMapper extends EntityMapper<OrganizationDTO, Organization> {


    @Mapping(target = "restaurantUsers", ignore = true)
    Organization toEntity(OrganizationDTO dto);

    @Mapping(target = "restaurantUsers", ignore = true)
    OrganizationDTO toDto(Organization entity);
}
