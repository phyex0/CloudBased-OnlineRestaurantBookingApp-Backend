package com.upsoon.common.kafkaTemplateDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

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
