package com.example.chatdemo.service.safety;

import org.springframework.stereotype.Service;

@Service
public class SafetyGuardService {

    public boolean shouldBlock(String text) {
        if (text == null || text.isBlank()) {
            return false;
        }
        String normalized = text.toLowerCase();
        return normalized.contains("kill")
                || normalized.contains("suicide")
                || normalized.contains("minor")
                || normalized.contains("force");
    }
}
