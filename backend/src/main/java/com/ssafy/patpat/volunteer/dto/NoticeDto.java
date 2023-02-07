package com.ssafy.patpat.volunteer.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class NoticeDto {
    private Long noticeId;
    private Integer shelterId;
    private String title;
    private List<VolunteerScheduleDto> list;
}
