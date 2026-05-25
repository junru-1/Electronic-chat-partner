package com.example.chatdemo.api.chat.dto;

import com.example.chatdemo.domain.chat.ConversationMessage;
import com.example.chatdemo.domain.chat.MessageRole;
import java.time.LocalDateTime;

public record MessageResponse(
        Long id,
        MessageRole role,
        String content,
        String riskFlag,
        LocalDateTime createdAt
) {

    public static MessageResponse from(ConversationMessage message) {
        return new MessageResponse(
                message.getId(),
                message.getRole(),
                message.getContent(),
                message.getRiskFlag(),
                message.getCreatedAt()
        );
    }
}
