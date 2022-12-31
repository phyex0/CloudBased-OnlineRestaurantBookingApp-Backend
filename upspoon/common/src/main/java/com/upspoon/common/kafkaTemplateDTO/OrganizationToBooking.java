package com.upspoon.common.kafkaTemplateDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author burak.yesildal
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationToBooking implements Serializable {
    private static final long serialVersionUID = 4686359501357881408L;

    private UUID organizationId;

    private String organizationName;

    private Integer maxTable;
}
