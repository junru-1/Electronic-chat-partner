package com.example.chatdemo.domain.chat;

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
@Table(name = "conversation_messages")
public class ConversationMessage extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "session_id", nullable = false)
    private ConversationSession session;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private MessageRole role;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(length = 50)
    private String riskFlag;

    @Column
    private Long promptSnapshotId;

    protected ConversationMessage() {
    }

    public ConversationMessage(ConversationSession session, MessageRole role, String content, String riskFlag, Long promptSnapshotId) {
        this.session = session;
        this.role = role;
        this.content = content;
        this.riskFlag = riskFlag;
        this.promptSnapshotId = promptSnapshotId;
    }

    public ConversationSession getSession() {
        return session;
    }

    public MessageRole getRole() {
        return role;
    }

    public String getContent() {
        return content;
    }

    public String getRiskFlag() {
        return riskFlag;
    }

    public Long getPromptSnapshotId() {
        return promptSnapshotId;
    }
}
