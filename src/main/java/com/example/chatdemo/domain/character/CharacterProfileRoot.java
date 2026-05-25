package com.example.chatdemo.domain.character;

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
@Table(name = "characters")
public class CharacterProfileRoot extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserAccount user;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 50)
    private String relationshipType;

    @Column(length = 100)
    private String nicknameForUser;

    @Column(length = 50)
    private String genderIdentity;

    @Column(columnDefinition = "TEXT")
    private String summary;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private CharacterStatus status;

    protected CharacterProfileRoot() {
    }

    public CharacterProfileRoot(
            UserAccount user,
            String name,
            String relationshipType,
            String nicknameForUser,
            String genderIdentity,
            String summary,
            CharacterStatus status
    ) {
        this.user = user;
        this.name = name;
        this.relationshipType = relationshipType;
        this.nicknameForUser = nicknameForUser;
        this.genderIdentity = genderIdentity;
        this.summary = summary;
        this.status = status;
    }

    public UserAccount getUser() {
        return user;
    }

    public String getName() {
        return name;
    }

    public String getRelationshipType() {
        return relationshipType;
    }

    public String getNicknameForUser() {
        return nicknameForUser;
    }

    public String getGenderIdentity() {
        return genderIdentity;
    }

    public String getSummary() {
        return summary;
    }

    public CharacterStatus getStatus() {
        return status;
    }

    public void updateBasics(String name, String relationshipType, String nicknameForUser, String genderIdentity, String summary) {
        this.name = name;
        this.relationshipType = relationshipType;
        this.nicknameForUser = nicknameForUser;
        this.genderIdentity = genderIdentity;
        this.summary = summary;
    }

    public void changeStatus(CharacterStatus status) {
        this.status = status;
    }
}
