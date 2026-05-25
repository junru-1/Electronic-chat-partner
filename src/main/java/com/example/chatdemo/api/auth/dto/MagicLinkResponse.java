package com.example.chatdemo.api.auth.dto;

public record MagicLinkResponse(
        String token,
        String loginUrl
) {
}
