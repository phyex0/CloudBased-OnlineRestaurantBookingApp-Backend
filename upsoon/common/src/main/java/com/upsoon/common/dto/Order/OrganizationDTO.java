package com.upsoon.common.dto.Order;

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
public class OrganizationDTO implements Serializable {

    @NotNull
    private String organizationName;

    private BusinessDTO market;

    private BusinessDTO restaurant;

}
