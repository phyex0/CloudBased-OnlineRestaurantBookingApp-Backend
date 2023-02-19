package com.upspoon.gateway.controller.authorization;

import com.upspoon.common.dto.AuthorizationUser.AuthenticateRequest;
import com.upspoon.common.dto.AuthorizationUser.AuthenticationResponse;
import com.upspoon.common.dto.AuthorizationUser.RegisterRequest;
import com.upspoon.gateway.client.authorization.AuthorizationClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author burak.yesildal
 */
@RestController
@RequestMapping("/api/authentication")
@RequiredArgsConstructor
public class AuthorizationController {

    private final AuthorizationClient authorizationClient;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return authorizationClient.register(request);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticateRequest request) {
        return authorizationClient.authenticate(request);
    }
}
