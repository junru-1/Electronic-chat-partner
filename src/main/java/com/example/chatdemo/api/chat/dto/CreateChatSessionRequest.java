package com.example.chatdemo.api.chat.dto;

import com.example.chatdemo.domain.chat.ScenarioMode;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateChatSessionRequest(
        @NotNull Long characterId,
        @NotNull ScenarioMode scenarioMode,
        @NotNull @Min(0) @Max(100) Integer moodLevel,
        @Size(max = 255) String title
) {
}
