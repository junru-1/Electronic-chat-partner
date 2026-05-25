package com.example.chatdemo.service.chat;

import com.example.chatdemo.api.chat.dto.ChatSessionResponse;
import com.example.chatdemo.api.chat.dto.CreateChatSessionRequest;
import com.example.chatdemo.api.common.BusinessException;
import com.example.chatdemo.api.common.ErrorCode;
import com.example.chatdemo.domain.chat.ConversationSession;
import com.example.chatdemo.domain.character.Character;
import com.example.chatdemo.domain.user.UserAccount;
import com.example.chatdemo.repository.character.CharacterRepository;
import com.example.chatdemo.repository.chat.ConversationSessionRepository;
import com.example.chatdemo.repository.user.UserAccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChatSessionService {

    private final ConversationSessionRepository conversationSessionRepository;
    private final CharacterRepository characterRepository;
    private final UserAccountRepository userAccountRepository;

    public ChatSessionService(
            ConversationSessionRepository conversationSessionRepository,
            CharacterRepository characterRepository,
            UserAccountRepository userAccountRepository
    ) {
        this.conversationSessionRepository = conversationSessionRepository;
        this.characterRepository = characterRepository;
        this.userAccountRepository = userAccountRepository;
    }

    @Transactional
    public ChatSessionResponse create(Long userId, CreateChatSessionRequest request) {
        UserAccount user = userAccountRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.UNAUTHORIZED, "User not found"));
        Character character = characterRepository.findById(request.characterId())
                .orElseThrow(() -> new BusinessException(ErrorCode.CHARACTER_NOT_FOUND));

        ConversationSession session = new ConversationSession(
                character,
                user,
                request.scenarioMode(),
                request.moodLevel(),
                request.title()
        );

        return ChatSessionResponse.from(conversationSessionRepository.save(session));
    }
}
