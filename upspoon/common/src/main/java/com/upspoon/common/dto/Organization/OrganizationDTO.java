package com.upspoon.common.dto.Organization;

import com.upspoon.common.enums.BusinessTypes;
import com.upspoon.common.enums.PackageService;
import lombok.*;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

/**
 * @author burak.yesildal
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class OrganizationDTO implements Serializable {
    private static final long serialVersionUID = -4310059631778009167L;

    private UUID id;

    private String organizationName;


    private PackageService packageService;


    private OrganizationDTO parentOrganization;


    private Set<OrganizationDTO> childOrganizations;


    private String fullAddress;


    private Set<RestaurantUserDTO> restaurantUsers;

    private BusinessTypes businessType;
}
