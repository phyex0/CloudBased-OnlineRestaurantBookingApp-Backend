package com.upspoon.gateway.client.user;

import com.upspoon.common.config.CustomFeignConfiguration;
import com.upspoon.common.dto.User.UserDataDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@FeignClient(name = "${client.user-api.name}", url = "${client.user-api.url}", configuration = CustomFeignConfiguration.class)
public interface UserClient {

    @PostMapping("/api/user/create")
    ResponseEntity<UUID> createUser(@RequestBody UserDataDTO userDataDTO);

    @GetMapping("/api/user")
    ResponseEntity<UserDataDTO> getUserByMail(@RequestParam String mail);


    @PutMapping("/api/user")
    ResponseEntity<Void> updateUser(@RequestBody UserDataDTO userDataDTO);

    @DeleteMapping("/api/user")
    ResponseEntity<Void> deleteUserById(@RequestParam UUID id);

    @PutMapping("/api/user/create-order")
    ResponseEntity<Void> createOrder(@RequestParam String mail, @RequestParam UUID orderId);
}
