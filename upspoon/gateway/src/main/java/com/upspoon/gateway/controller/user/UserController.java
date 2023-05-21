package com.upspoon.gateway.controller.user;

import com.upspoon.common.dto.User.UserDataDTO;
import com.upspoon.gateway.client.user.UserClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    private final UserClient userClient;

    @PostMapping("/create")
    public ResponseEntity<UUID> createUser(@RequestBody UserDataDTO userDataDTO) {
        return userClient.createUser(userDataDTO);
    }

    @GetMapping
    public ResponseEntity<UserDataDTO> getUserByMail(@RequestParam String mail) {
        return userClient.getUserByMail(mail);
    }


    @PutMapping
    public ResponseEntity<Void> updateUser(@RequestBody UserDataDTO userDataDTO) {
        return userClient.updateUser(userDataDTO);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUserById(@RequestParam UUID id) {
        return userClient.deleteUserById(id);
    }

    @PutMapping("/create-order")
    public ResponseEntity<Void> createOrder(@RequestParam String mail, @RequestParam UUID orderId) {
        return userClient.createOrder(mail, orderId);
    }
}
