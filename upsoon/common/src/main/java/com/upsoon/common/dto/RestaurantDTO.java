package com.upsoon.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDTO implements Serializable {
    private String name;

    private String country;

    private String city;

    private String distinct;

    private String fullAddress;

    private String zipCode;


    private UserDTO userDTO;
}
