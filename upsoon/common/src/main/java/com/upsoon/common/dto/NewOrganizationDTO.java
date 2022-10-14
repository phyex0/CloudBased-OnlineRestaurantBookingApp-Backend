package com.upsoon.common.dto;

import com.upsoon.common.enums.PackageService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewOrganizationDTO implements Serializable {

    @NotNull
    private String organizationName;

    @NotNull
    private PackageService packageService;

    @NotNull
    private String fullAddress;

}
