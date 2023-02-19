package com.upspoon.gateway.client.authorization;

import com.upspoon.common.config.CustomFeignConfiguration;
import com.upspoon.common.dto.AuthorizationUser.AuthenticateRequest;
import com.upspoon.common.dto.AuthorizationUser.AuthenticationResponse;
import com.upspoon.common.dto.AuthorizationUser.RegisterRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author burak.yesildal
 */
@FeignClient(name = "${client.authorization-api.name}", url = "${client.authorization-api.url}", configuration = CustomFeignConfiguration.class)
public interface AuthorizationClient {

    @PostMapping("/api/authentication/register")
    ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request);


    @PostMapping("/api/authentication/authenticate")
    ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticateRequest request);
}
