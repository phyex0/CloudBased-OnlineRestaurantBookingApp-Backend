package com.upspoon.user.repository;

import com.upspoon.user.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserDataRepository extends JpaRepository<UserData, UUID> {

    Boolean existsByMailAddress(String mail);

    Optional<UserData> findByMailAddress(String mail);
}
