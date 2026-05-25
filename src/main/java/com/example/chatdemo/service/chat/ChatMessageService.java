package com.example.chatdemo.service.chat;

import com.example.chatdemo.api.chat.dto.MessageResponse;
import com.example.chatdemo.api.chat.dto.SendMessageResponse;
import com.example.chatdemo.domain.chat.ConversationMessage;
import com.example.chatdemo.domain.chat.ConversationSession;
import com.example.chatdemo.domain.chat.MessageRole;
import com.example.chatdemo.llm.LlmChatRequest;
import com.example.chatdemo.llm.LlmChatResponse;
import com.example.chatdemo.llm.LlmClient;
import com.example.chatdemo.repository.chat.ConversationMessageRepository;
import com.example.chatdemo.repository.chat.ConversationSessionRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChatMessageService {

    private final ConversationSessionRepository conversationSessionRepository;
    private final ConversationMessageRepository conversationMessageRepository;
    private final LlmClient llmClient;

    public ChatMessageService(
            ConversationSessionRepository conversationSessionRepository,
            ConversationMessageRepository conversationMessageRepository,
            LlmClient llmClient
    ) {
        this.conversationSessionRepository = conversationSessionRepository;
        this.conversationMessageRepository = conversationMessageRepository;
        this.llmClient = llmClient;
    }

    @Transactional(readOnly = true)
    public List<MessageResponse> list(Long sessionId) {
        return conversationMessageRepository.findAllBySessionIdOrderByCreatedAtAsc(sessionId)
                .stream()
                .map(MessageResponse::from)
                .toList();
    }

    @Transactional
    public SendMessageResponse send(Long sessionId, String content) {
        ConversationSession session = conversationSessionRepository.findById(sessionId)
                .orElseThrow(() -> new IllegalArgumentException("Session not found: " + sessionId));

        ConversationMessage userMessage = conversationMessageRepository.save(
                new ConversationMessage(session, MessageRole.USER, content, null, null)
        );

        LlmChatResponse llmResponse = llmClient.chat(new LlmChatRequest(
                "You are simulating a close relationship partner. Keep the tone stable and natural.",
                List.of(new LlmChatRequest.LlmMessage("user", content)),
                null
        ));

        ConversationMessage assistantMessage = conversationMessageRepository.save(
                new ConversationMessage(session, MessageRole.ASSISTANT, llmResponse.content(), null, null)
        );

        return new SendMessageResponse(
                MessageResponse.from(userMessage),
                MessageResponse.from(assistantMessage),
                null
        );
    }
}
