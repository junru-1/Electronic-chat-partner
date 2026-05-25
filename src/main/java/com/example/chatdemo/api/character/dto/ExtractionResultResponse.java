package com.example.chatdemo.api.character.dto;

import java.util.List;

public record ExtractionResultResponse(
        Long characterId,
        Integer profileVersion,
        String profileJson,
        String styleSummary,
        List<MemoryItem> memories
) {

    public record MemoryItem(
            String type,
            String title,
            String content,
            Integer importanceScore
    ) {
    }
}
