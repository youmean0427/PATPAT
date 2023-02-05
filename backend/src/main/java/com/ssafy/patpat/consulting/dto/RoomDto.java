package com.ssafy.patpat.consulting.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomDto {
    String userName;
    String shelterName;
    int shelterId;
    int consultingId;
}
