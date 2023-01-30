package com.ssafy.patpat.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ssafy.patpat.common.dto.FileDto;
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
    private List<FileDto> fileUrlList;
    private int name;
    private String state;
}
