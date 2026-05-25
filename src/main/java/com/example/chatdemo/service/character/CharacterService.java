package com.example.chatdemo.service.character;

import com.example.chatdemo.api.character.dto.CharacterResponse;
import com.example.chatdemo.api.character.dto.CreateCharacterRequest;
import com.example.chatdemo.api.common.BusinessException;
import com.example.chatdemo.api.common.ErrorCode;
import com.example.chatdemo.domain.character.Character;
import com.example.chatdemo.domain.character.CharacterStatus;
import com.example.chatdemo.domain.user.UserAccount;
import com.example.chatdemo.repository.character.CharacterRepository;
import com.example.chatdemo.repository.user.UserAccountRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CharacterService {

    private final CharacterRepository characterRepository;
    private final UserAccountRepository userAccountRepository;

    public CharacterService(CharacterRepository characterRepository, UserAccountRepository userAccountRepository) {
        this.characterRepository = characterRepository;
        this.userAccountRepository = userAccountRepository;
    }

    @Transactional(readOnly = true)
    public List<CharacterResponse> listByUser(Long userId) {
        return characterRepository.findAllByUserIdOrderByUpdatedAtDesc(userId)
                .stream()
                .map(CharacterResponse::from)
                .toList();
    }

    @Transactional
    public CharacterResponse create(Long userId, CreateCharacterRequest request) {
        UserAccount user = userAccountRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.UNAUTHORIZED, "User not found"));

        Character character = new Character(
                user,
                request.name(),
                request.relationshipType(),
                request.nicknameForUser(),
                request.genderIdentity(),
                request.summary(),
                request.status() == null ? CharacterStatus.DRAFT : request.status()
        );

        return CharacterResponse.from(characterRepository.save(character));
    }
}
