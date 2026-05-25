package com.example.chatdemo.repository.character;

import com.example.chatdemo.domain.character.CharacterSource;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterSourceRepository extends JpaRepository<CharacterSource, Long> {

    List<CharacterSource> findAllByCharacterIdAndDeletedFalseOrderByCreatedAtAsc(Long characterId);
}
