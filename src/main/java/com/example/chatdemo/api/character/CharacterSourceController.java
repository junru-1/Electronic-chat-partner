package com.example.chatdemo.api.character;

import com.example.chatdemo.api.character.dto.AddCharacterSourceRequest;
import com.example.chatdemo.api.character.dto.CharacterSourceResponse;
import com.example.chatdemo.api.character.dto.ExtractionResultResponse;
import com.example.chatdemo.service.character.CharacterExtractionService;
import com.example.chatdemo.service.character.CharacterSourceService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/characters/{characterId}")
public class CharacterSourceController {

    private final CharacterSourceService characterSourceService;
    private final CharacterExtractionService characterExtractionService;

    public CharacterSourceController(
            CharacterSourceService characterSourceService,
            CharacterExtractionService characterExtractionService
    ) {
        this.characterSourceService = characterSourceService;
        this.characterExtractionService = characterExtractionService;
    }

    @GetMapping("/sources")
    public List<CharacterSourceResponse> listSources(@PathVariable Long characterId) {
        return characterSourceService.list(characterId);
    }

    @PostMapping("/sources")
    public CharacterSourceResponse addSource(
            @PathVariable Long characterId,
            @Valid @RequestBody AddCharacterSourceRequest request
    ) {
        return characterSourceService.add(characterId, request);
    }

    @PostMapping("/extract")
    public ExtractionResultResponse extract(@PathVariable Long characterId) {
        return characterExtractionService.extract(characterId);
    }
}
