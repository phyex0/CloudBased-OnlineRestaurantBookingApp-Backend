package com.upspoon.order.mapper;

import com.upspoon.common.dto.Order.OrganizationDTO;
import com.upspoon.common.mapper.EntityMapper;
import com.upspoon.order.model.Organization;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author burak.yesildal
 */


@Mapper(componentModel = "spring", uses = {MenuMapper.class})
public interface OrganizationMapper extends EntityMapper<OrganizationDTO, Organization> {
    @Mapping(target = "exactOrganizationId", source = "id")
    Organization toEntity(OrganizationDTO organizationDTO);

    @Mapping(target = "id", source = "exactOrganizationId")
    OrganizationDTO toDto(Organization organization);
}
