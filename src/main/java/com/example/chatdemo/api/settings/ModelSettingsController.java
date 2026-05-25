package com.example.chatdemo.api.settings;

import com.example.chatdemo.api.settings.dto.ModelSettingsResponse;
import com.example.chatdemo.service.settings.ModelSettingsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/settings/model")
public class ModelSettingsController {

    private final ModelSettingsService modelSettingsService;

    public ModelSettingsController(ModelSettingsService modelSettingsService) {
        this.modelSettingsService = modelSettingsService;
    }

    @GetMapping
    public ModelSettingsResponse get() {
        return modelSettingsService.getCurrentSettings();
    }
}
