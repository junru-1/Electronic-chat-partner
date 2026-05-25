package com.example.chatdemo.api.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record VerifyMagicLinkRequest(
        @NotBlank String token
) {
}
