package com.ssafy.patpat.common.code.converter;

import com.ssafy.patpat.common.code.ConsultingState;
import com.ssafy.patpat.common.code.TimeState;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class TimeStateConverter implements AttributeConverter<TimeState, Integer> {
    @Override
    public Integer convertToDatabaseColumn(TimeState timeState) {
        if (timeState == null){
            return null;
        }
        return timeState.getCode();
    }

    @Override
    public TimeState convertToEntityAttribute(Integer code) {
        if (code == null){
            return null;
        }
        return Stream.of(TimeState.values())
                .filter(c -> c.getCode()==code)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
