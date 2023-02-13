package com.ssafy.patpat.volunteer.dto;

import lombok.*;

import java.time.LocalDate;
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
    private Long shelterId;
    private String title;
    private LocalDate volunteerDate;
    private List<VolunteerScheduleDto> list;
}
