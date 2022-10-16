package com.upsoon.common.dto;

import com.upsoon.common.enums.PackageService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrganizationDTO implements Serializable {

    private String organizationName;

    private PackageService packageService;

    private String fullAddress;

}
