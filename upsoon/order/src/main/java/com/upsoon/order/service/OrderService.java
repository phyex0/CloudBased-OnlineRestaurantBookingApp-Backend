package com.upsoon.order.service;

import com.upsoon.common.kafkaTemplateDTO.OrganizationToOrder;

public interface OrderService {

    void saveOrganization(OrganizationToOrder organizationToOrder);
}
