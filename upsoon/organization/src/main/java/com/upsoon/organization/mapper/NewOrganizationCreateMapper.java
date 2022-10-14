package com.upsoon.organization.mapper;

import com.upsoon.common.dto.NewOrganizationDTO;
import com.upsoon.common.dto.OrganizationDTO;
import com.upsoon.common.mapper.EntityMapper;
import com.upsoon.organization.model.Organization;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface NewOrganizationCreateMapper extends EntityMapper<NewOrganizationDTO, Organization> {

    Organization toEntity(NewOrganizationDTO newOrganizationDTO);

    NewOrganizationDTO toDto(Organization organization);
}
