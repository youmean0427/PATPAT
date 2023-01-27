package com.ssafy.patpat.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestBoardDto {
    private int typeCode;
    private int offSet;
    private int limit;
}
