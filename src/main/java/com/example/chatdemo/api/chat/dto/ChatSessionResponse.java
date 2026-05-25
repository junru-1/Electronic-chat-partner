package com.example.chatdemo.api.chat.dto;

import com.example.chatdemo.domain.chat.ConversationSession;
import com.example.chatdemo.domain.chat.ScenarioMode;
import java.time.LocalDateTime;

public record ChatSessionResponse(
        Long id,
        Long characterId,
        ScenarioMode scenarioMode,
        Integer moodLevel,
        String title,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public static ChatSessionResponse from(ConversationSession session) {
        return new ChatSessionResponse(
                session.getId(),
                session.getCharacter().getId(),
                session.getScenarioMode(),
                session.getMoodLevel(),
                session.getTitle(),
                session.getCreatedAt(),
                session.getUpdatedAt()
        );
    }
}
