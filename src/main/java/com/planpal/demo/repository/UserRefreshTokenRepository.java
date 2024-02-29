package com.planpal.demo.repository;

import com.planpal.demo.domain.User;
import com.planpal.demo.domain.UserRefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRefreshTokenRepository extends JpaRepository<UserRefreshToken, Long> {
    Optional<UserRefreshToken> findByUser(User user);
}
