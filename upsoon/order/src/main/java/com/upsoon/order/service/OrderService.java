package com.upsoon.order.service;

import com.upsoon.common.kafkaTemplateDTO.OrganizationToOrder;

/**
 * @author Halit Burak Yeşildal
 */

public interface OrderService {

    void saveOrganization(OrganizationToOrder organizationToOrder);
}
