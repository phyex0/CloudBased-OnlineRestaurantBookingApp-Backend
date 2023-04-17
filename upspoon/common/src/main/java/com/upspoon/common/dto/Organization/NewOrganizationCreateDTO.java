package com.upspoon.common.dto.Organization;

import jakarta.validation.constraints.NotNull;
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
public class NewOrganizationCreateDTO implements Serializable {
    private static final long serialVersionUID = -6700905242753566691L;

    @NotNull
    private NewOrganizationDTO newOrganizationDTO;

    @NotNull
    private NewRestaurantUserDTO newRestaurantUserDTO;

}
