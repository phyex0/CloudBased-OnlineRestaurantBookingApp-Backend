package com.upspoon.authorization.repository;

import com.upspoon.authorization.model.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * @author burak.yesildal
 */
@Repository
public interface UserDetailRepository extends JpaRepository<UserDetail, UUID> {

    Optional<UserDetail> findByEmail(String userName);
}
