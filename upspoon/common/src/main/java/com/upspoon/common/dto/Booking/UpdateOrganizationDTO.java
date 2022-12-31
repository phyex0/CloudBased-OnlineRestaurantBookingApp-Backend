package com.upspoon.common.dto.Booking;

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
public class UpdateOrganizationDTO implements Serializable {
    private static final long serialVersionUID = 2142035925140364203L;

    private UUID organizationId;
    private String organizationName;
    private Integer maxTable;
}
