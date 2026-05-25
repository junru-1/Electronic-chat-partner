package com.example.chatdemo.domain.character;

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
@Table(name = "character_sources")
public class CharacterSource extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "character_id", nullable = false)
    private CharacterProfileRoot character;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private SourceType sourceType;

    @Column(length = 255)
    private String title;

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String rawText;

    @Column(nullable = false)
    private boolean deleted;

    protected CharacterSource() {
    }

    public CharacterSource(CharacterProfileRoot character, SourceType sourceType, String title, String rawText) {
        this.character = character;
        this.sourceType = sourceType;
        this.title = title;
        this.rawText = rawText;
        this.deleted = false;
    }

    public CharacterProfileRoot getCharacter() {
        return character;
    }

    public SourceType getSourceType() {
        return sourceType;
    }

    public String getTitle() {
        return title;
    }

    public String getRawText() {
        return rawText;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void markDeleted() {
        this.deleted = true;
    }
}
