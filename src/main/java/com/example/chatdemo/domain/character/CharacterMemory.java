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
@Table(name = "character_memories")
public class CharacterMemory extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "character_id", nullable = false)
    private CharacterProfileRoot character;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private MemoryType memoryType;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private Integer importanceScore;

    @Column(columnDefinition = "JSON")
    private String sourceIdsJson;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private MemoryCreatedBy createdBy;

    protected CharacterMemory() {
    }

    public CharacterMemory(
            CharacterProfileRoot character,
            MemoryType memoryType,
            String title,
            String content,
            Integer importanceScore,
            String sourceIdsJson,
            MemoryCreatedBy createdBy
    ) {
        this.character = character;
        this.memoryType = memoryType;
        this.title = title;
        this.content = content;
        this.importanceScore = importanceScore;
        this.sourceIdsJson = sourceIdsJson;
        this.createdBy = createdBy;
    }

    public CharacterProfileRoot getCharacter() {
        return character;
    }

    public MemoryType getMemoryType() {
        return memoryType;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Integer getImportanceScore() {
        return importanceScore;
    }

    public String getSourceIdsJson() {
        return sourceIdsJson;
    }

    public MemoryCreatedBy getCreatedBy() {
        return createdBy;
    }
}
