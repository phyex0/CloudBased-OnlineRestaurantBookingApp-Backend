package com.upsoon.common.dto.Organization;

import com.upsoon.common.enums.PackageService;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

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

    private String organizationName;


    private PackageService packageService;


    private OrganizationDTO parentOrganization;


    private Set<OrganizationDTO> childOrganizations;


    private String fullAddress;


    private Set<RestaurantUserDTO> restaurantUsers;
}
