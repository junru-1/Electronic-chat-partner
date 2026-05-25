package com.example.chatdemo.api.character.dto;

import com.example.chatdemo.domain.character.CharacterStatus;
import com.example.chatdemo.domain.character.CharacterProfileRoot;
import java.time.LocalDateTime;

public record CharacterResponse(
        Long id,
        String name,
        String relationshipType,
        String nicknameForUser,
        String genderIdentity,
        String summary,
        CharacterStatus status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public static CharacterResponse from(CharacterProfileRoot character) {
        return new CharacterResponse(
                character.getId(),
                character.getName(),
                character.getRelationshipType(),
                character.getNicknameForUser(),
                character.getGenderIdentity(),
                character.getSummary(),
                character.getStatus(),
                character.getCreatedAt(),
                character.getUpdatedAt()
        );
    }
}
