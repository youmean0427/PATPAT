package com.ssafy.patpat.volunteer.dto;

import com.ssafy.patpat.common.service.FileService;
import com.ssafy.patpat.volunteer.entity.VolunteerReservation;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationUserDto {
    private Long reservationId;
    private Long userId;
    private String userName;
    private String userProfile;
    private Integer userExp;
    private Integer capacity;

    public ReservationUserDto(VolunteerReservation vr){
        this.reservationId = vr.getReservationId();
        this.userId = vr.getUser().getUserId();
        this.userName = vr.getUser().getNickname();
        this.capacity = vr.getCapacity();
        this.userExp = vr.getUser().getExp();
    }

}
