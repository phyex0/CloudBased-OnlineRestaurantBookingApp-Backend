package com.upspoon.common.dto.AuthorizationUser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author burak.yesildal
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenDTO implements Serializable {
    private static final long serialVersionUID = 1685259686246122192L;

    private UUID userId;
    private String accessToken;
    private String refreshToken;
}