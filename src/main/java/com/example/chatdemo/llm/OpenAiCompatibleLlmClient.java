package com.example.chatdemo.llm;

import com.example.chatdemo.config.LlmProperties;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class OpenAiCompatibleLlmClient implements LlmClient {

    private final RestClient restClient;
    private final LlmProperties llmProperties;

    public OpenAiCompatibleLlmClient(RestClient.Builder restClientBuilder, LlmProperties llmProperties) {
        this.llmProperties = llmProperties;
        this.restClient = restClientBuilder
                .baseUrl(trimTrailingSlash(llmProperties.baseUrl()))
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + llmProperties.apiKey())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Override
    @SuppressWarnings("unchecked")
    public LlmChatResponse chat(LlmChatRequest request) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("model", request.modelOverride() == null || request.modelOverride().isBlank() ? llmProperties.model() : request.modelOverride());
        payload.put("messages", toMessages(request));

        Map<String, Object> response = restClient.post()
                .uri("/chat/completions")
                .body(payload)
                .retrieve()
                .body(Map.class);

        String content = "";
        if (response != null) {
            Object choicesObject = response.get("choices");
            if (choicesObject instanceof List<?> choices && !choices.isEmpty()) {
                Object firstChoice = choices.get(0);
                if (firstChoice instanceof Map<?, ?> choiceMap) {
                    Object messageObject = choiceMap.get("message");
                    if (messageObject instanceof Map<?, ?> messageMap) {
                        Object responseContent = messageMap.get("content");
                        if (responseContent instanceof String text) {
                            content = text;
                        }
                    }
                }
            }
        }

        String model = response == null ? llmProperties.model() : String.valueOf(response.getOrDefault("model", llmProperties.model()));
        return new LlmChatResponse(model, content, String.valueOf(response));
    }

    private List<Map<String, String>> toMessages(LlmChatRequest request) {
        List<Map<String, String>> messages = new java.util.ArrayList<>();
        if (request.systemPrompt() != null && !request.systemPrompt().isBlank()) {
            messages.add(Map.of("role", "system", "content", request.systemPrompt()));
        }
        for (LlmChatRequest.LlmMessage message : request.messages()) {
            messages.add(Map.of("role", message.role(), "content", message.content()));
        }
        return messages;
    }

    private String trimTrailingSlash(String baseUrl) {
        if (baseUrl == null) {
            return "";
        }
        return baseUrl.endsWith("/") ? baseUrl.substring(0, baseUrl.length() - 1) : baseUrl;
    }
}
