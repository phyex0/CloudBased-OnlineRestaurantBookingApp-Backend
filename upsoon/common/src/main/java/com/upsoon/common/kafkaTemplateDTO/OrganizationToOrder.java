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
public class OrganizationToOrder implements Serializable {
    private static final long serialVersionUID = -7965361783288377954L;

    private UUID organizationId;

    private String organizationName;

    private boolean isMarket;

    private boolean isRestaurant;


}
