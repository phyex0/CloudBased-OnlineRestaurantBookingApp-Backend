package com.upspoon.authorization.service;

import com.upspoon.authorization.mapper.UserDetailMapper;
import com.upspoon.authorization.model.UserDetail;
import com.upspoon.authorization.repository.UserDetailRepository;
import com.upspoon.common.dto.AuthorizationUser.UserCreateDTO;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserOperationServiceImpl implements UserOperationService {
    private final UserDetailMapper userDetailMapper;
    private final UserDetailRepository userDetailRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public ResponseEntity<Void> createUser(UserCreateDTO userCreateDTO) {
        UserDetail entity = userDetailMapper.toEntity(userCreateDTO);
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));

        Optional<UserDetail> user = userDetailRepository.findByEmail(entity.getEmail());

        if (user.isPresent())
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        userDetailRepository.save(entity);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
