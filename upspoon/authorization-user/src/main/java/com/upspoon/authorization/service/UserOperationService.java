package com.upspoon.authorization.service;

import com.upspoon.common.dto.AuthorizationUser.UserCreateDTO;
import org.springframework.http.ResponseEntity;

public interface UserOperationService {

    ResponseEntity<Void> createUser(UserCreateDTO userCreateDTO);

}
