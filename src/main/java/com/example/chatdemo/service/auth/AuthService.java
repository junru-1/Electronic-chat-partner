package com.example.chatdemo.service.auth;

import com.example.chatdemo.domain.auth.LoginToken;
import com.example.chatdemo.domain.user.UserAccount;
import com.example.chatdemo.repository.auth.LoginTokenRepository;
import com.example.chatdemo.repository.user.UserAccountRepository;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final UserAccountRepository userAccountRepository;
    private final LoginTokenRepository loginTokenRepository;

    public AuthService(UserAccountRepository userAccountRepository, LoginTokenRepository loginTokenRepository) {
        this.userAccountRepository = userAccountRepository;
        this.loginTokenRepository = loginTokenRepository;
    }

    @Transactional
    public String requestMagicLink(String email, long ttlMinutes) {
        UserAccount user = userAccountRepository.findByEmail(email)
                .orElseGet(() -> userAccountRepository.save(new UserAccount(email, null)));

        String token = UUID.randomUUID().toString();
        LoginToken loginToken = new LoginToken(user, token, LocalDateTime.now().plusMinutes(ttlMinutes));
        loginTokenRepository.save(loginToken);
        return token;
    }

    @Transactional
    public UserAccount verify(String token) {
        LoginToken loginToken = loginTokenRepository.findByToken(token)
                .orElseThrow(() -> new IllegalArgumentException("Token not found"));

        if (loginToken.getUsedAt() != null || loginToken.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Token expired or already used");
        }

        loginToken.markUsed(LocalDateTime.now());
        return loginToken.getUser();
    }
}
