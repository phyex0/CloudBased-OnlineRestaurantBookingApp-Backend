package com.upspoon.user.service;


import com.upspoon.common.dto.User.UserDataDTO;
import com.upspoon.common.exceptions.UserAlreadyExistException;
import com.upspoon.common.exceptions.UserNotFoundException;
import com.upspoon.user.client.AuthorizationServerClient;
import com.upspoon.user.mapper.AuthorizationUserCreateMapper;
import com.upspoon.user.mapper.UserDataMapper;
import com.upspoon.user.model.UserData;
import com.upspoon.user.repository.UserDataRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDataMapper userDataMapper;

    private final UserDataRepository userDataRepository;

    private final AuthorizationServerClient authorizationServerClient;

    private final AuthorizationUserCreateMapper authorizationUserCreateMapper;


    @Override
    @Transactional
    public ResponseEntity<UUID> createUser(UserDataDTO userDataDTO) {
        if (userDataRepository.existsByMailAddress(userDataDTO.getMailAddress()))
            throw new UserAlreadyExistException();
        UserData userData = userDataMapper.toEntity(userDataDTO);

        UserData entity = userDataRepository.save(userData);

        try {
            authorizationServerClient.createUser(authorizationUserCreateMapper.toEntity(userDataDTO));
        } catch (Exception e) {
            throw new RuntimeException();
        }

        return new ResponseEntity<>(entity.getId(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDataDTO> getUserByMail(String mail) {
        UserData userData = userDataRepository.findByMailAddress(mail).orElseThrow(UserNotFoundException::new);
        return new ResponseEntity<>(userDataMapper.toDto(userData), HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<Void> updateUser(UserDataDTO userDataDTO) {
        UserData userData = userDataRepository.findByMailAddress(userDataDTO.getMailAddress()).orElseThrow(UserNotFoundException::new);
        userData = userDataMapper.updateEntity(userData, userDataDTO);
        userDataRepository.save(userData);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<Void> deleteUser(UUID id) {
        userDataRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<Void> createOrder(String mail, UUID orderId) {
        UserData userData = userDataRepository.findByMailAddress(mail).orElseThrow(UserNotFoundException::new);
        userData.getOrderHistory().add(orderId);
        userDataRepository.save(userData);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
