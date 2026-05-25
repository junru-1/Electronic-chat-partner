package com.example.chatdemo.llm;

public record LlmChatResponse(
        String model,
        String content,
        String rawResponse
) {
}
