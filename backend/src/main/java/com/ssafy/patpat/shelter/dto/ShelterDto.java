package com.ssafy.patpat.shelter.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ssafy.patpat.common.dto.FileDto;
import com.ssafy.patpat.consulting.entity.Time;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShelterDto {
    private int shelterId;
    private String name;
    private String address;
    @Schema(example = "fileUrl:.png")
    private FileDto fileDto;
    @Schema(example = "설명")
    private String infoContent;
    @Schema(example = "전화번호")
    private String phoneNum;
    @Schema(example = "내 상담 가능 시간 리스트")
    private List<Time> timeList;
}
