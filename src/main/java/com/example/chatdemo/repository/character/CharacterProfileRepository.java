package com.example.chatdemo.repository.character;

import com.example.chatdemo.domain.character.CharacterProfile;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterProfileRepository extends JpaRepository<CharacterProfile, Long> {

    Optional<CharacterProfile> findTopByCharacterIdOrderByVersionDesc(Long characterId);
}
