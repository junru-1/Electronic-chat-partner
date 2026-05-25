package com.example.chatdemo.repository.chat;

import com.example.chatdemo.domain.chat.ConversationSession;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationSessionRepository extends JpaRepository<ConversationSession, Long> {

    List<ConversationSession> findAllByCharacterIdOrderByUpdatedAtDesc(Long characterId);
}
