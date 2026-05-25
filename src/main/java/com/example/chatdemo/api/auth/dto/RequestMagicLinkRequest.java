package com.example.chatdemo.api.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RequestMagicLinkRequest(
        @NotBlank @Email String email
) {
}
