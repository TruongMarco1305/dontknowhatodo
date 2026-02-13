package io.github.dontknowhatodo.user;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, UUID> {
    Optional<UserInfo> findByUsername(String username);
    Optional<UserInfo> findByEmail(String email);
}