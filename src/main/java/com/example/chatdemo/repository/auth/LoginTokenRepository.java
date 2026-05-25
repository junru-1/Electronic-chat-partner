package com.example.chatdemo.repository.auth;

import com.example.chatdemo.domain.auth.LoginToken;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginTokenRepository extends JpaRepository<LoginToken, Long> {

    Optional<LoginToken> findByToken(String token);
}
