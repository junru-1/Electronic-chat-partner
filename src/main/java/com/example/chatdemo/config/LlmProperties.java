package com.example.chatdemo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.llm")
public record LlmProperties(
        String baseUrl,
        String apiKey,
        String model,
        int timeoutSeconds
) {
}
