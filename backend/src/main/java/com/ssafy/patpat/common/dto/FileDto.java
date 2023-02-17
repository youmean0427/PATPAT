package com.ssafy.patpat.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Builder
public class FileDto {
    private Long id;
    private String origFilename;
    private String filename;
    private String filePath;
}
