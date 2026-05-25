package com.example.chatdemo.domain.safety;

import com.example.chatdemo.domain.chat.ConversationMessage;
import com.example.chatdemo.domain.chat.ConversationSession;
import com.example.chatdemo.domain.character.CharacterProfileRoot;
import com.example.chatdemo.domain.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "safety_events")
public class SafetyEvent extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "character_id")
    private CharacterProfileRoot character;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id")
    private ConversationSession session;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "message_id")
    private ConversationMessage message;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private SafetyEventType eventType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private SafetySeverity severity;

    @Column(nullable = false, columnDefinition = "JSON")
    private String detailJson;

    protected SafetyEvent() {
    }

    public SafetyEvent(
            CharacterProfileRoot character,
            ConversationSession session,
            ConversationMessage message,
            SafetyEventType eventType,
            SafetySeverity severity,
            String detailJson
    ) {
        this.character = character;
        this.session = session;
        this.message = message;
        this.eventType = eventType;
        this.severity = severity;
        this.detailJson = detailJson;
    }

    public CharacterProfileRoot getCharacter() {
        return character;
    }

    public ConversationSession getSession() {
        return session;
    }

    public ConversationMessage getMessage() {
        return message;
    }

    public SafetyEventType getEventType() {
        return eventType;
    }

    public SafetySeverity getSeverity() {
        return severity;
    }

    public String getDetailJson() {
        return detailJson;
    }
}
