package com.example.chatdemo.service.character;

import com.example.chatdemo.api.character.dto.AddCharacterSourceRequest;
import com.example.chatdemo.api.character.dto.CharacterSourceResponse;
import com.example.chatdemo.api.common.BusinessException;
import com.example.chatdemo.api.common.ErrorCode;
import com.example.chatdemo.domain.character.Character;
import com.example.chatdemo.domain.character.CharacterSource;
import com.example.chatdemo.repository.character.CharacterRepository;
import com.example.chatdemo.repository.character.CharacterSourceRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CharacterSourceService {

    private final CharacterRepository characterRepository;
    private final CharacterSourceRepository characterSourceRepository;

    public CharacterSourceService(CharacterRepository characterRepository, CharacterSourceRepository characterSourceRepository) {
        this.characterRepository = characterRepository;
        this.characterSourceRepository = characterSourceRepository;
    }

    @Transactional(readOnly = true)
    public List<CharacterSourceResponse> list(Long characterId) {
        return characterSourceRepository.findAllByCharacterIdAndDeletedFalseOrderByCreatedAtAsc(characterId)
                .stream()
                .map(CharacterSourceResponse::from)
                .toList();
    }

    @Transactional
    public CharacterSourceResponse add(Long characterId, AddCharacterSourceRequest request) {
        Character character = characterRepository.findById(characterId)
                .orElseThrow(() -> new BusinessException(ErrorCode.CHARACTER_NOT_FOUND));

        CharacterSource source = new CharacterSource(character, request.sourceType(), request.title(), request.rawText());
        return CharacterSourceResponse.from(characterSourceRepository.save(source));
    }
}
