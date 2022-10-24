package com.upsoon.organization.mapper;

import com.upsoon.common.kafkaTemplateDTO.OrganizationToOrder;
import com.upsoon.common.mapper.EntityMapper;
import com.upsoon.organization.model.Organization;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {})
public interface RestaurantKafkaEventMapper extends EntityMapper<OrganizationToOrder, Organization> {

    @Mapping(target = "organizationId", source = "id")
    OrganizationToOrder toDto(Organization organization);

    @Mapping(target = "id", source = "organizationId")
    Organization toEntity(OrganizationToOrder organizationToOrder);

}
