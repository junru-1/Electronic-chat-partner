package com.example.chatdemo.repository.chat;

import com.example.chatdemo.domain.chat.ConversationMessage;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationMessageRepository extends JpaRepository<ConversationMessage, Long> {

    List<ConversationMessage> findAllBySessionIdOrderByCreatedAtAsc(Long sessionId);
}
