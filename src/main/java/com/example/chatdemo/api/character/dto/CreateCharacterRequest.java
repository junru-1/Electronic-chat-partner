package com.example.chatdemo.api.character.dto;

import com.example.chatdemo.domain.character.CharacterStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateCharacterRequest(
        @NotBlank @Size(max = 100) String name,
        @NotBlank @Size(max = 50) String relationshipType,
        @Size(max = 100) String nicknameForUser,
        @Size(max = 50) String genderIdentity,
        @Size(max = 2000) String summary,
        CharacterStatus status
) {
}
