package com.example.chatdemo.api.character.dto;

import com.example.chatdemo.domain.character.CharacterSource;
import com.example.chatdemo.domain.character.SourceType;
import java.time.LocalDateTime;

public record CharacterSourceResponse(
        Long id,
        SourceType sourceType,
        String title,
        String rawText,
        LocalDateTime createdAt
) {

    public static CharacterSourceResponse from(CharacterSource source) {
        return new CharacterSourceResponse(
                source.getId(),
                source.getSourceType(),
                source.getTitle(),
                source.getRawText(),
                source.getCreatedAt()
        );
    }
}
