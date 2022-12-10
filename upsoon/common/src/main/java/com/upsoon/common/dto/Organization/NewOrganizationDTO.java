package com.upsoon.common.dto.Organization;

import com.upsoon.common.enums.PackageService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
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

    private boolean isMarket;

    private boolean isRestaurant;

    private boolean isBooking;

}
