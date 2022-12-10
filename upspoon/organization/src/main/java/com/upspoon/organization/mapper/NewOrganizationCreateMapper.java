package com.upspoon.organization.mapper;

import com.upspoon.common.dto.Organization.NewOrganizationDTO;
import com.upspoon.common.dto.Organization.UpdateOrganizationDTO;
import com.upspoon.common.mapper.EntityMapper;
import com.upspoon.organization.model.Organization;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * @author burak.yesildal
 */

@Mapper(componentModel = "spring", uses = {}, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface NewOrganizationCreateMapper extends EntityMapper<NewOrganizationDTO, Organization> {

    Organization toEntity(NewOrganizationDTO newOrganizationDTO);

    NewOrganizationDTO toDto(Organization organization);

    Organization updateEntity(@MappingTarget Organization organization, UpdateOrganizationDTO newOrganizationDTO);
}
