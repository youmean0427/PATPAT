package com.ssafy.patpat.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ssafy.patpat.common.dto.FileDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Builder
public class FavoriteDto {
    private int protectId;
    private int userId;
    @Schema(example = "FileUrl:.png")
    private List<FileDto> fileUrlList;
    private String name;
    private String state;
}
