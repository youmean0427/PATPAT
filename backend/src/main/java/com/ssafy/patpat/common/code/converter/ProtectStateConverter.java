package com.ssafy.patpat.common.code.converter;

import com.ssafy.patpat.common.code.ProtectState;
import com.ssafy.patpat.common.code.Reservation;

import javax.persistence.AttributeConverter;
import java.util.stream.Stream;

public class ProtectStateConverter implements AttributeConverter<ProtectState, Integer> {
    @Override
    public Integer convertToDatabaseColumn(ProtectState protectState) {
        if (protectState == null){
            return null;
        }
        return protectState.getCode();
    }

    @Override
    public ProtectState convertToEntityAttribute(Integer code) {
        if (code == null){
            return null;
        }
        return Stream.of(ProtectState.values())
                .filter(c -> c.getCode()==code)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
