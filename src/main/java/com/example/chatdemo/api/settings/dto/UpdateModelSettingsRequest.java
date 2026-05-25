package com.example.chatdemo.api.settings.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateModelSettingsRequest(
        @NotBlank String baseUrl,
        @NotBlank String apiKey,
        @NotBlank String model
) {
}
