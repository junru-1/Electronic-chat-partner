package com.example.chatdemo.api.chat.dto;

public record SendMessageResponse(
        MessageResponse userMessage,
        MessageResponse assistantMessage,
        String riskFlag
) {
}
