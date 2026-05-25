package com.example.chatdemo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.auth.magic-link")
public record MagicLinkProperties(
        long ttlMinutes,
        String appBaseUrl
) {
}
