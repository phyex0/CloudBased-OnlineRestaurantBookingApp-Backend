package com.upsoon.common.dto.Organization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Halit Burak Yeşildal
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
