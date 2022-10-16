package com.upsoon.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRestaurantUserDTO implements Serializable {

    private String name;

    private String lastName;

    private String middleName;

    private String phoneNumber;

    private String email;

}
