package com.upsoon.organization.mapper;

import com.upsoon.common.dto.Organization.NewOrganizationDTO;
import com.upsoon.common.dto.Organization.UpdateOrganizationDTO;
import com.upsoon.common.mapper.EntityMapper;
import com.upsoon.organization.model.Organization;
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
