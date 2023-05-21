package com.upspoon.common.dto.AuthorizationUser;

import com.upspoon.common.enums.Role;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class UserCreateDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -2381973614314326757L;

    private String firstname;

    private String lastname;

    private String email;

    private String password;

    private Role role;
}
