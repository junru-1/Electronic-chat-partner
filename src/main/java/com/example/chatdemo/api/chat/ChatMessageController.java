package com.example.chatdemo.api.chat;

import com.example.chatdemo.api.chat.dto.MessageResponse;
import com.example.chatdemo.api.chat.dto.SendMessageRequest;
import com.example.chatdemo.api.chat.dto.SendMessageResponse;
import com.example.chatdemo.api.common.ApiResponse;
import com.example.chatdemo.service.chat.ChatMessageService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat/sessions/{sessionId}/messages")
public class ChatMessageController {

    private final ChatMessageService chatMessageService;

    public ChatMessageController(ChatMessageService chatMessageService) {
        this.chatMessageService = chatMessageService;
    }

    @GetMapping
    public ApiResponse<List<MessageResponse>> list(@PathVariable Long sessionId) {
        return ApiResponse.success(chatMessageService.list(sessionId));
    }

    @PostMapping
    public ApiResponse<SendMessageResponse> send(
            @PathVariable Long sessionId,
            @Valid @RequestBody SendMessageRequest request
    ) {
        return ApiResponse.success(chatMessageService.send(sessionId, request.content()));
    }
}
