package com.example.chatdemo.api.chat;

import com.example.chatdemo.api.chat.dto.ChatSessionResponse;
import com.example.chatdemo.api.chat.dto.CreateChatSessionRequest;
import com.example.chatdemo.service.chat.ChatSessionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat/sessions")
public class ChatSessionController {

    private final ChatSessionService chatSessionService;

    public ChatSessionController(ChatSessionService chatSessionService) {
        this.chatSessionService = chatSessionService;
    }

    @PostMapping
    public ChatSessionResponse create(
            @RequestHeader("X-User-Id") Long userId,
            @Valid @RequestBody CreateChatSessionRequest request
    ) {
        return chatSessionService.create(userId, request);
    }
}
