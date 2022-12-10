package com.upspoon.order.mapper;

import com.upspoon.common.dto.Order.OrganizationDTO;
import com.upspoon.common.mapper.EntityMapper;
import com.upspoon.order.model.Organization;
import org.mapstruct.Mapper;

/**
 * @author burak.yesildal
 */


@Mapper(componentModel = "spring", uses = {BusinessMapper.class})
public interface OrganizationMapper extends EntityMapper<OrganizationDTO, Organization> {

    Organization toEntity(OrganizationDTO organizationDTO);

    OrganizationDTO toDto(Organization organization);
}
