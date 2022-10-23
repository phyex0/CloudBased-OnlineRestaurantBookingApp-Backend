package com.upsoon.common.kafkaTemplateDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author Halit Burak Yeşildal
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(of = {"id", "message", "messageDate"})
public class OrganizationToOrder extends AbstractBaseKafkaTemplate implements Serializable {
    private UUID organizationId;

    private String organizationName;

    private boolean isMarket;

    private boolean isRestaurant;


}
