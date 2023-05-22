package com.upspoon.user.service;

import com.upspoon.common.dto.User.UserDataDTO;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface UserService {
    ResponseEntity<UUID> createUser(UserDataDTO userDataDTO);

    ResponseEntity<UserDataDTO> getUserByMail(String mail);

    ResponseEntity<Void> updateUser(UserDataDTO userDataDTO);

    ResponseEntity<Void> deleteUser(UUID id);

    ResponseEntity<Void> createOrder(String mail, UUID orderId);
}
