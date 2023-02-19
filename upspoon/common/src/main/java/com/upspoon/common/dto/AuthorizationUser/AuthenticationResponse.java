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
public class AuthenticationResponse implements Serializable {
    private static final long serialVersionUID = 1685259686246122192L;

    private String token;
}
