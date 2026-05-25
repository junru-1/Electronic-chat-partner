package com.example.chatdemo.api.settings.dto;

public record ModelSettingsResponse(
        String baseUrl,
        String model,
        boolean apiKeyConfigured
) {
}
