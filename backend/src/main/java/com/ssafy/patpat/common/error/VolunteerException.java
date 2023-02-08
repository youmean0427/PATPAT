package com.ssafy.patpat.common.error;

import com.ssafy.patpat.volunteer.entity.VolunteerReservation;

public class VolunteerException extends Exception{
    public VolunteerException(String msg){
        super(msg);
    }
}
