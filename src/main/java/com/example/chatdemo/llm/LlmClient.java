package com.example.chatdemo.llm;

public interface LlmClient {

    LlmChatResponse chat(LlmChatRequest request);
}
