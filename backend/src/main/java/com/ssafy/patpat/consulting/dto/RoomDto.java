package com.ssafy.patpat.consulting.dto;

import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomDto {
    String userName;
    String shelterName;
    Long shelterId;
    Long consultingId;
}
