package com.example.chatdemo.llm;

import java.util.List;

public record LlmChatRequest(
        String systemPrompt,
        List<LlmMessage> messages,
        String modelOverride
) {

    public record LlmMessage(String role, String content) {
    }
}
