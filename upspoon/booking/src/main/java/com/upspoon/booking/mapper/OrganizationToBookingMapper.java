package com.upspoon.booking.mapper;

import com.upspoon.booking.model.Organization;
import com.upspoon.common.kafkaTemplateDTO.OrganizationToBooking;
import com.upspoon.common.mapper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author burak.yesildal
 */

@Mapper(componentModel = "spring")
public interface OrganizationToBookingMapper extends EntityMapper<OrganizationToBooking, Organization> {

    @Override
    @Mapping(target = "exactOrganizationId", source = "organizationId")
    @Mapping(target = "organizationName", source = "organizationName")
    @Mapping(target = "maxTable", source = "maxTable")
    Organization toEntity(OrganizationToBooking dto);
}
