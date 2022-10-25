package com.upsoon.common.dto.Organization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

/**
 * @author Halit Burak Yeşildal
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewRestaurantUserDTO implements Serializable {

    private UUID userId;

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
