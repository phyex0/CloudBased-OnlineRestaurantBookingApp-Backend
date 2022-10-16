package com.upsoon.organization.mapper;

import com.upsoon.common.dto.NewOrganizationDTO;
import com.upsoon.common.dto.UpdateOrganizationDTO;
import com.upsoon.common.mapper.EntityMapper;
import com.upsoon.organization.model.Organization;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {}, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface NewOrganizationCreateMapper extends EntityMapper<NewOrganizationDTO, Organization> {

    Organization toEntity(NewOrganizationDTO newOrganizationDTO);

    NewOrganizationDTO toDto(Organization organization);

    Organization updateEntity(@MappingTarget Organization organization, UpdateOrganizationDTO newOrganizationDTO);
}
