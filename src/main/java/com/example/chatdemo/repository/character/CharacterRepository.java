package com.example.chatdemo.repository.character;

import com.example.chatdemo.domain.character.CharacterProfileRoot;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<CharacterProfileRoot, Long> {

    List<CharacterProfileRoot> findAllByUserIdOrderByUpdatedAtDesc(Long userId);
}
