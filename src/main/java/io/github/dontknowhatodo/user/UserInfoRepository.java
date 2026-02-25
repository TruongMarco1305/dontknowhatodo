package io.github.dontknowhatodo.user;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserInfoRepository extends JpaRepository<UserInfo, UUID> {
    Optional<UserInfo> findByUsername(String username);
    Optional<UserInfo> findByEmail(String email);
    @Query("SELECT u FROM UserInfo u WHERE u.email = :input OR u.username = :input")
    Optional<UserInfo> findByEmailOrUsername(String input);
}