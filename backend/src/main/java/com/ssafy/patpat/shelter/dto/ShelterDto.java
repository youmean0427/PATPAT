package com.ssafy.patpat.shelter.dto;

import com.ssafy.patpat.common.dto.FileDto;
import com.ssafy.patpat.consulting.entity.Time;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.core.io.FileSystemResource;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ShelterDto {
    private Long shelterId;
    private String name;
    private String address;
//    @Schema(example = "fileUrl:.png")
//    private FileDto fileDto;
//    @Schema(example = "fileUrl:.png")
//    private List<FileDto> fileDtoList;
    private List<FileDto> imageList;
    @Schema(example = "설명")
    private String infoContent;
    @Schema(example = "전화번호")
    private String phoneNumber;
    @Schema(example = "내 상담 가능 시간 리스트")
    private List<Time> timeList;
    private String sidoCode;
    private String sidoName;
    private String gugunCode;
    private String gugunName;
    private String ownerName;
    private String latitude;
    private String longitude;
    private Long ownerId;
}
