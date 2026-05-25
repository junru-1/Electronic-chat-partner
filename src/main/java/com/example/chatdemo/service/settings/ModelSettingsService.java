package com.example.chatdemo.service.settings;

import com.example.chatdemo.api.settings.dto.ModelSettingsResponse;
import com.example.chatdemo.config.LlmProperties;
import org.springframework.stereotype.Service;

@Service
public class ModelSettingsService {

    private final LlmProperties llmProperties;

    public ModelSettingsService(LlmProperties llmProperties) {
        this.llmProperties = llmProperties;
    }

    public ModelSettingsResponse getCurrentSettings() {
        return new ModelSettingsResponse(
                llmProperties.baseUrl(),
                llmProperties.model(),
                llmProperties.apiKey() != null && !llmProperties.apiKey().isBlank()
        );
    }
}
