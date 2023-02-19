package com.upspoon.authorization.service;

import com.upspoon.common.dto.AuthorizationUser.AuthenticateRequest;
import com.upspoon.common.dto.AuthorizationUser.AuthenticationResponse;
import com.upspoon.common.dto.AuthorizationUser.RegisterRequest;
import org.springframework.http.ResponseEntity;

/**
 * @author burak.yesildal
 */
public interface AuthenticationService {

    ResponseEntity<AuthenticationResponse> register(RegisterRequest request);

    ResponseEntity<AuthenticationResponse> authenticate(AuthenticateRequest request);
}
