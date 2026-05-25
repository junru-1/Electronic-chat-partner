package com.example.chatdemo.api.character;

import com.example.chatdemo.api.character.dto.CharacterResponse;
import com.example.chatdemo.api.character.dto.CreateCharacterRequest;
import com.example.chatdemo.service.character.CharacterService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/characters")
public class CharacterController {

    private final CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping
    public List<CharacterResponse> list(@RequestHeader("X-User-Id") Long userId) {
        return characterService.listByUser(userId);
    }

    @PostMapping
    public CharacterResponse create(@RequestHeader("X-User-Id") Long userId, @Valid @RequestBody CreateCharacterRequest request) {
        return characterService.create(userId, request);
    }
}
