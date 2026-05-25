package com.example.chatdemo.domain.character;

import com.example.chatdemo.domain.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "character_profiles")
public class CharacterProfile extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "character_id", nullable = false)
    private Character character;

    @Column(nullable = false)
    private Integer version;

    @Column(nullable = false, columnDefinition = "JSON")
    private String profileJson;

    @Column(columnDefinition = "TEXT")
    private String styleSummary;

    @Column(columnDefinition = "TEXT")
    private String speakingDos;

    @Column(columnDefinition = "TEXT")
    private String speakingDonts;

    @Column(columnDefinition = "JSON")
    private String generatedFromSourceIds;

    protected CharacterProfile() {
    }

    public CharacterProfile(
            Character character,
            Integer version,
            String profileJson,
            String styleSummary,
            String speakingDos,
            String speakingDonts,
            String generatedFromSourceIds
    ) {
        this.character = character;
        this.version = version;
        this.profileJson = profileJson;
        this.styleSummary = styleSummary;
        this.speakingDos = speakingDos;
        this.speakingDonts = speakingDonts;
        this.generatedFromSourceIds = generatedFromSourceIds;
    }

    public Character getCharacter() {
        return character;
    }

    public Integer getVersion() {
        return version;
    }

    public String getProfileJson() {
        return profileJson;
    }

    public String getStyleSummary() {
        return styleSummary;
    }

    public String getSpeakingDos() {
        return speakingDos;
    }

    public String getSpeakingDonts() {
        return speakingDonts;
    }

    public String getGeneratedFromSourceIds() {
        return generatedFromSourceIds;
    }
}
