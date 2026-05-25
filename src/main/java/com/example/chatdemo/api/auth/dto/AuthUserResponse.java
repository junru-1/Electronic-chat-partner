package com.example.chatdemo.api.auth.dto;

public record AuthUserResponse(
        Long id,
        String email,
        String displayName
) {
}
