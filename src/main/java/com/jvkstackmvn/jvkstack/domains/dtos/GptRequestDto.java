package com.jvkstackmvn.jvkstack.domains.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GptRequestDto {
    private String model;
    private List<MessageDto> messages;
}
