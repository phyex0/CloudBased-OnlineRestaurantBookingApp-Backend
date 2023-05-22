package com.upspoon.authorization.controller;

import com.upspoon.authorization.service.UserOperationService;
import com.upspoon.common.dto.AuthorizationUser.UserCreateDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user-operation")
@AllArgsConstructor
public class UserOperationController {

    private final UserOperationService userOperationService;

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody UserCreateDTO userCreateDTO) {
        return userOperationService.createUser(userCreateDTO);
    }
}
