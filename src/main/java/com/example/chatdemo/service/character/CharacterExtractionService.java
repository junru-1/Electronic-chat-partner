package com.example.chatdemo.service.character;

import com.example.chatdemo.api.character.dto.ExtractionResultResponse;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CharacterExtractionService {

    public ExtractionResultResponse extract(Long characterId) {
        return new ExtractionResultResponse(
                characterId,
                1,
                "{}",
                "Extraction pipeline not implemented yet.",
                List.of()
        );
    }
}
