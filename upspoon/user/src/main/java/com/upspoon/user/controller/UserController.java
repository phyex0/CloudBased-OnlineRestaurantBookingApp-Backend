package com.upspoon.user.controller;

import com.upspoon.common.dto.User.UserDataDTO;
import com.upspoon.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UUID> createUser(@RequestBody UserDataDTO userDataDTO) {
        return userService.createUser(userDataDTO);
    }

    @GetMapping
    public ResponseEntity<UserDataDTO> getUserByMail(@RequestParam String mail) {
        return userService.getUserByMail(mail);
    }


    @PutMapping
    public ResponseEntity<Void> updateUser(@RequestBody UserDataDTO userDataDTO) {
        return userService.updateUser(userDataDTO);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUserById(@RequestParam UUID id) {
        return userService.deleteUser(id);
    }

    @PutMapping("/create-order")
    public ResponseEntity<Void> createOrder(@RequestParam String mail, @RequestParam UUID orderId) {
        return userService.createOrder(mail, orderId);
    }
}
