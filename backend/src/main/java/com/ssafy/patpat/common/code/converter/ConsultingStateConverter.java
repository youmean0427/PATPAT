package com.ssafy.patpat.common.code.converter;

import com.ssafy.patpat.common.code.ConsultingState;
import com.ssafy.patpat.common.code.ProtectState;

import javax.persistence.AttributeConverter;
import java.util.stream.Stream;

public class ConsultingStateConverter implements AttributeConverter<ConsultingState, Integer> {
    @Override
    public Integer convertToDatabaseColumn(ConsultingState consultingState) {
        if (consultingState == null){
            return null;
        }
        return consultingState.getCode();
    }

    @Override
    public ConsultingState convertToEntityAttribute(Integer code) {
        if (code == null){
            return null;
        }
        return Stream.of(ConsultingState.values())
                .filter(c -> c.getCode()==code)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
