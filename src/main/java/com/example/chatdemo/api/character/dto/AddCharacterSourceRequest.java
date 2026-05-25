package com.example.chatdemo.api.character.dto;

import com.example.chatdemo.domain.character.SourceType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AddCharacterSourceRequest(
        @NotNull SourceType sourceType,
        @Size(max = 255) String title,
        @NotBlank String rawText
) {
}
