package com.upsoon.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewRestaurantUserDTO implements Serializable {

    @NotNull
    private String name;

    @NotNull
    private String lastName;

    private String middleName;

    @NotNull
    private String phoneNumber;

    @NotNull
    private String email;

}
