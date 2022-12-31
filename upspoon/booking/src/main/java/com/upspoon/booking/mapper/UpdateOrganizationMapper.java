package com.upspoon.booking.mapper;

import com.upspoon.booking.model.Organization;
import com.upspoon.common.dto.Booking.UpdateOrganizationDTO;
import com.upspoon.common.mapper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * @author burak.yesildal
 */

@Mapper(componentModel = "spring")
public interface UpdateOrganizationMapper extends EntityMapper<UpdateOrganizationDTO, Organization> {

    Organization updateEntity(UpdateOrganizationDTO updateOrganizationDTO, @MappingTarget Organization organization);

}
