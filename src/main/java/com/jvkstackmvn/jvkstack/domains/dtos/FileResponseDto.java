package com.jvkstackmvn.jvkstack.domains.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileResponseDto {
    private String name;
    private String url;
    private String type;
    private long size;
}
