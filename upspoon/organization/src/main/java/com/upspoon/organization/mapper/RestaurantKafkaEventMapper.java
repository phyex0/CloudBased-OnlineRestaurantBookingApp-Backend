package com.upspoon.organization.mapper;

import com.upspoon.common.kafkaTemplateDTO.OrganizationToOrder;
import com.upspoon.common.mapper.EntityMapper;
import com.upspoon.organization.model.Organization;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {})
public interface RestaurantKafkaEventMapper extends EntityMapper<OrganizationToOrder, Organization> {

    @Mapping(target = "organizationId", source = "id")
    @Mapping(target = "businessTypes", source = "businessType")
    OrganizationToOrder toDto(Organization organization);

    @Mapping(target = "id", source = "organizationId")
    @Mapping(target = "businessType", source = "businessTypes")
    Organization toEntity(OrganizationToOrder organizationToOrder);

}
