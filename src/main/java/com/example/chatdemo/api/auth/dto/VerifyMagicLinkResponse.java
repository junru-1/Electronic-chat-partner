package com.example.chatdemo.api.auth.dto;

public record VerifyMagicLinkResponse(
        String accessToken,
        AuthUserResponse user
) {
}
