package com.upsoon.common.dto;

import com.upsoon.common.enums.PackageService;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

public class OrganizationDTO  implements Serializable {

    private String organizationName;


    private PackageService packageService;

      private UserDTO userDTO;


    private List<RestaurantDTO> restaurantDTO;
}
