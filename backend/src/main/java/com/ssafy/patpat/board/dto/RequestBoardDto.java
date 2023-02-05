package com.ssafy.patpat.board.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestBoardDto {
    @Schema(example = "(0==입양후기     1==무료나눔     2==정보 공유")
    private int typeCode;
    private int offSet;
    private int limit;
}
