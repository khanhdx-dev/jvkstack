package com.jvkstackmvn.jvkstack.services.external;

import com.jvkstackmvn.jvkstack.domains.dtos.GptRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "openai", url = "https://api.openai.com/v1/chat")
public interface GptApi {
    @PostMapping("/completions")
    String send(@RequestBody GptRequestDto input, @RequestHeader("Authorization") String bearerToken);
}
