package com.upsoon.common.dto;

import com.upsoon.common.enums.PackageService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationDTO implements Serializable {

    private String organizationName;

    private PackageService packageService;

    private UserDTO userDTO;

    private List<RestaurantDTO> restaurantDTO;
}
