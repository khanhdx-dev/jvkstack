package com.jvkstackmvn.jvkstack.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jvkstackmvn.jvkstack.domains.dtos.GptRequestDto;
import com.jvkstackmvn.jvkstack.domains.dtos.MessageDto;
import com.jvkstackmvn.jvkstack.services.external.GptApi;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/completion")
public class CompletionController {

    @Autowired
    private GptApi api;

    @PostMapping("")
    public Map<String, Object> gptCompletion(@RequestBody String input){

        GptRequestDto gptRequestDto = GptRequestDto.builder()
                .model("gpt-3.5-turbo")
                .messages(Arrays.asList(MessageDto.builder()
                        .role("user")
                        .content(input)
                        .build()))
                .build();

        String hihi = api.send(gptRequestDto, "Bearer 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");

        try {
            Map<String, Object> mapping = new ObjectMapper().readValue(hihi, HashMap.class);
            return mapping;
        }
        catch (JsonProcessingException e) {
            return Map.of();
        }

    }
}
