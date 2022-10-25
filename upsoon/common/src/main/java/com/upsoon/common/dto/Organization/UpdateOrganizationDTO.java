package com.upsoon.common.dto.Organization;

import com.upsoon.common.enums.PackageService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Halit Burak Yeşildal
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrganizationDTO implements Serializable {

    private String organizationName;

    private PackageService packageService;

    private String fullAddress;

}
