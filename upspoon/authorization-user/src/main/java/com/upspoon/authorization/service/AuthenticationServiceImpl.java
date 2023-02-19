package com.upspoon.authorization.service;

import com.upspoon.authorization.model.AuthUser;
import com.upspoon.authorization.repository.AuthUserRepository;
import com.upspoon.common.dto.AuthorizationUser.AuthenticateRequest;
import com.upspoon.common.dto.AuthorizationUser.AuthenticationResponse;
import com.upspoon.common.dto.AuthorizationUser.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author burak.yesildal
 */
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthUserRepository authUserRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;


    @Override
    public ResponseEntity<AuthenticationResponse> register(RegisterRequest request) {
        var user = AuthUser.builder()
                .firstname(request.getFirstName())
                .lastname(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        authUserRepository.save(user);
        var jwtToken = jwtService.generateToken(user);


        return new ResponseEntity<>(new AuthenticationResponse(jwtToken), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AuthenticationResponse> authenticate(AuthenticateRequest request) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        var user = authUserRepository.findAuthUserByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);

        return new ResponseEntity<>(new AuthenticationResponse(jwtToken), HttpStatus.OK);
    }
}
