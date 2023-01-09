package com.upspoon.order.mapper;

import com.upspoon.common.kafkaTemplateDTO.OrganizationToOrder;
import com.upspoon.common.mapper.EntityMapper;
import com.upspoon.order.model.Organization;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author burak.yesildal
 */


@Mapper(componentModel = "spring", uses = {})
public interface OrganizationFromOrganizationServiceMapper extends EntityMapper<OrganizationToOrder, Organization> {

    @Mapping(source = "organizationId", target = "exactOrganizationId")
    @Mapping(target = "businessTypes", source = "businessTypes")
    Organization toEntity(OrganizationToOrder organizationToOrder);

    @Mapping(target = "organizationId", source = "exactOrganizationId")
    OrganizationToOrder toDto(Organization organization);

}
