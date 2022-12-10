package com.upsoon.common.dto.Organization;

import com.upsoon.common.enums.PackageService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author burak.yesildal
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrganizationDTO implements Serializable {
    private static final long serialVersionUID = 997364663153317328L;

    private String organizationName;

    private PackageService packageService;

    private String fullAddress;

}
