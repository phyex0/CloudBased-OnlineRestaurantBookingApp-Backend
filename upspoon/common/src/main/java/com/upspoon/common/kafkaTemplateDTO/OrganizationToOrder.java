package com.upspoon.common.kafkaTemplateDTO;

import com.upspoon.common.enums.BusinessTypes;
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
public class OrganizationToOrder implements Serializable {
    private static final long serialVersionUID = -7965361783288377954L;

    private UUID organizationId;

    private String organizationName;

    private BusinessTypes businessTypes;


}
