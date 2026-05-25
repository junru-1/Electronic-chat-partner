package com.example.chatdemo.domain.chat;

import com.example.chatdemo.domain.character.Character;
import com.example.chatdemo.domain.common.BaseEntity;
import com.example.chatdemo.domain.user.UserAccount;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "conversation_sessions")
public class ConversationSession extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "character_id", nullable = false)
    private Character character;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserAccount user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private ScenarioMode scenarioMode;

    @Column(nullable = false)
    private Integer moodLevel;

    @Column(length = 255)
    private String title;

    protected ConversationSession() {
    }

    public ConversationSession(Character character, UserAccount user, ScenarioMode scenarioMode, Integer moodLevel, String title) {
        this.character = character;
        this.user = user;
        this.scenarioMode = scenarioMode;
        this.moodLevel = moodLevel;
        this.title = title;
    }

    public Character getCharacter() {
        return character;
    }

    public UserAccount getUser() {
        return user;
    }

    public ScenarioMode getScenarioMode() {
        return scenarioMode;
    }

    public Integer getMoodLevel() {
        return moodLevel;
    }

    public String getTitle() {
        return title;
    }
}
