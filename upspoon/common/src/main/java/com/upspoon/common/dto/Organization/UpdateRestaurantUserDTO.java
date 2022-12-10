package com.upspoon.common.dto.Organization;

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
public class UpdateRestaurantUserDTO implements Serializable {
    private static final long serialVersionUID = 1957309793754257670L;

    private String name;

    private String lastName;

    private String middleName;

    private String phoneNumber;

    private String email;

}
