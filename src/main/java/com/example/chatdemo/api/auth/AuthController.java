package com.example.chatdemo.api.auth;

import com.example.chatdemo.api.auth.dto.AuthUserResponse;
import com.example.chatdemo.api.auth.dto.MagicLinkResponse;
import com.example.chatdemo.api.auth.dto.RequestMagicLinkRequest;
import com.example.chatdemo.api.auth.dto.VerifyMagicLinkRequest;
import com.example.chatdemo.api.auth.dto.VerifyMagicLinkResponse;
import com.example.chatdemo.api.common.ApiResponse;
import com.example.chatdemo.config.MagicLinkProperties;
import com.example.chatdemo.domain.user.UserAccount;
import com.example.chatdemo.service.auth.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/magic-link")
public class AuthController {

    private final AuthService authService;
    private final MagicLinkProperties magicLinkProperties;

    public AuthController(AuthService authService, MagicLinkProperties magicLinkProperties) {
        this.authService = authService;
        this.magicLinkProperties = magicLinkProperties;
    }

    @PostMapping("/request")
    public ApiResponse<MagicLinkResponse> request(@Valid @RequestBody RequestMagicLinkRequest request) {
        String token = authService.requestMagicLink(request.email(), magicLinkProperties.ttlMinutes());
        String loginUrl = magicLinkProperties.appBaseUrl() + "/auth/verify?token=" + token;
        return ApiResponse.success(new MagicLinkResponse(token, loginUrl));
    }

    @PostMapping("/verify")
    public ApiResponse<VerifyMagicLinkResponse> verify(@Valid @RequestBody VerifyMagicLinkRequest request) {
        UserAccount user = authService.verify(request.token());
        return ApiResponse.success(new VerifyMagicLinkResponse(
                request.token(),
                new AuthUserResponse(user.getId(), user.getEmail(), user.getDisplayName())
        ));
    }
}
