package com.upsoon.order.mapper;

import com.upsoon.common.dto.Order.OrganizationDTO;
import com.upsoon.common.mapper.EntityMapper;
import com.upsoon.order.model.Organization;
import org.mapstruct.Mapper;

/**
 * @author Halit Burak Yeşildal
 */


@Mapper(componentModel = "spring", uses = {BusinessMapper.class})
public interface OrganizationMapper extends EntityMapper<OrganizationDTO, Organization> {

    Organization toEntity(OrganizationDTO organizationDTO);

    OrganizationDTO toDto(Organization organization);
}
