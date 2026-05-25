package com.example.chatdemo.domain.chat;

import com.example.chatdemo.domain.character.Character;
import com.example.chatdemo.domain.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "prompt_snapshots")
public class PromptSnapshot extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "character_id", nullable = false)
    private Character character;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "session_id", nullable = false)
    private ConversationSession session;

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String systemPrompt;

    @Column(nullable = false, columnDefinition = "JSON")
    private String inputContextJson;

    @Column(nullable = false, length = 100)
    private String modelName;

    protected PromptSnapshot() {
    }

    public PromptSnapshot(Character character, ConversationSession session, String systemPrompt, String inputContextJson, String modelName) {
        this.character = character;
        this.session = session;
        this.systemPrompt = systemPrompt;
        this.inputContextJson = inputContextJson;
        this.modelName = modelName;
    }

    public Character getCharacter() {
        return character;
    }

    public ConversationSession getSession() {
        return session;
    }

    public String getSystemPrompt() {
        return systemPrompt;
    }

    public String getInputContextJson() {
        return inputContextJson;
    }

    public String getModelName() {
        return modelName;
    }
}
