package com.upspoon.user.client;

import com.upspoon.common.config.CustomFeignConfiguration;
import com.upspoon.common.dto.AuthorizationUser.UserCreateDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "${client.authorization-server.name}", url = "${client.authorization-server.url}", configuration = {CustomFeignConfiguration.class})
public interface AuthorizationServerClient {

    @PostMapping(value = "/api/user-operation", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> createUser(@RequestBody UserCreateDTO userCreateDTO);
}
