package com.ssafy.patpat.common.code.converter;

import com.ssafy.patpat.common.code.Reservation;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class ReservationConverter implements AttributeConverter<Reservation, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Reservation reservation) {
        if (reservation == null){
            return null;
        }
        return reservation.getCode();
    }

    @Override
    public Reservation convertToEntityAttribute(Integer code) {
        if (code == null){
            return null;
        }
        return Stream.of(Reservation.values())
                .filter(c -> c.getCode()==code)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
