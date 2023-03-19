package com.upspoon.common.dto.Organization;

import com.upspoon.common.enums.BusinessTypes;
import com.upspoon.common.enums.PackageService;
import jakarta.validation.constraints.NotNull;
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
public class NewOrganizationDTO implements Serializable {
    private static final long serialVersionUID = -4270833131760983727L;

    private UUID organizationId;

    @NotNull
    private String organizationName;

    @NotNull
    private PackageService packageService;

    @NotNull
    private String fullAddress;
    @NotNull
    private BusinessTypes businessTypes;

}
