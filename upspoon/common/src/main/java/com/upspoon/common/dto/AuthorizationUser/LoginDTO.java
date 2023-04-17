package com.upspoon.common.dto.AuthorizationUser;

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
public class LoginDTO implements Serializable {
    private static final long serialVersionUID = -3147534070160076174L;
    private String email;
    private String password;
}
