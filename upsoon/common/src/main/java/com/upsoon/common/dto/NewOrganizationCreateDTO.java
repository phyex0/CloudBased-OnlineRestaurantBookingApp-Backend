package com.upsoon.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewOrganizationCreateDTO implements Serializable {

    @NotNull
    private NewOrganizationDTO newOrganizationDTO;

    @NotNull
    private NewRestaurantUserDTO newRestaurantUserDTO;

}
