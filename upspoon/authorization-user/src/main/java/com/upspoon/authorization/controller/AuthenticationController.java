package com.upspoon.authorization.controller;

import com.upspoon.authorization.service.AuthenticationService;
import com.upspoon.common.dto.AuthorizationUser.AuthenticateRequest;
import com.upspoon.common.dto.AuthorizationUser.AuthenticationResponse;
import com.upspoon.common.dto.AuthorizationUser.RegisterRequest;
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
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return authenticationService.register(request);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticateRequest request) {
        return authenticationService.authenticate(request);
    }
}
