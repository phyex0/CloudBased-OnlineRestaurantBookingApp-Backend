package com.upsoon.order.mapper;

import com.upsoon.common.enums.BusinessTypes;
import com.upsoon.common.kafkaTemplateDTO.OrganizationToOrder;
import com.upsoon.common.mapper.EntityMapper;
import com.upsoon.order.model.Business;
import com.upsoon.order.model.Organization;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * @author burak.yesildal
 */


@Mapper(componentModel = "spring", uses = {})
public interface OrganizationFromOrganizationServiceMapper extends EntityMapper<OrganizationToOrder, Organization> {

    @Mapping(source = "organizationId", target = "exactOrganizationId")
    @Mapping(target = "market", ignore = true)
    @Mapping(target = "restaurant", ignore = true)
    Organization toEntity(OrganizationToOrder organizationToOrder);

    @Mapping(target = "market", ignore = true)
    @Mapping(target = "restaurant", ignore = true)
    @Mapping(target = "organizationId", source = "exactOrganizationId")
    OrganizationToOrder toDto(Organization organization);

    @AfterMapping
    static void setBusinessType(@MappingTarget Organization organization, OrganizationToOrder organizationToOrder) {
        if (organizationToOrder.isMarket()) {
            organization.setMarket(new Business());
            organization.getMarket().setBusinessTypes(BusinessTypes.MARKET);
        }

        if (organizationToOrder.isRestaurant()) {
            organization.setRestaurant(new Business());
            organization.getRestaurant().setBusinessTypes(BusinessTypes.RESTAURANT);
        }


    }
}
