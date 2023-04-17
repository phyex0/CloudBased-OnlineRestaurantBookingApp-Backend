package com.upspoon.common.dto.AuthorizationUser;

import com.upspoon.common.enums.Role;
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
public class RegisterRequest implements Serializable {
    private static final long serialVersionUID = 6939439655985284960L;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
}
