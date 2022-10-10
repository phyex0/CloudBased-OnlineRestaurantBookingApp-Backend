package com.upsoon.organization.mapper;


import com.upsoon.common.dto.OrganizationDTO;
import com.upsoon.common.mapper.EntityMapper;
import com.upsoon.organization.model.Organization;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {})
public interface OrganizationMapper extends EntityMapper<OrganizationDTO, Organization> {


    Organization toEntity(OrganizationDTO dto);

    OrganizationDTO toDto(Organization entity);
}
