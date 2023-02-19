package com.upspoon.authorization.repository;

import com.upspoon.authorization.model.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * @author burak.yesildal
 */
@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser, UUID> {
    Optional<AuthUser> findAuthUserByEmail(String email);
}
