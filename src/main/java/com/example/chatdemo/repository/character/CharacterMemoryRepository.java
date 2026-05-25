package com.example.chatdemo.repository.character;

import com.example.chatdemo.domain.character.CharacterMemory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterMemoryRepository extends JpaRepository<CharacterMemory, Long> {

    List<CharacterMemory> findAllByCharacterIdOrderByImportanceScoreDescCreatedAtAsc(Long characterId);
}
