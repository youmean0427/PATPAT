package com.ssafy.patpat.common.code.converter;

import com.ssafy.patpat.common.code.ProtectState;
import com.ssafy.patpat.common.code.TimeCode;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class TimeCodeConverter implements AttributeConverter<TimeCode, Integer> {
    @Override
    public Integer convertToDatabaseColumn(TimeCode timeCode) {
        if (timeCode == null){
            return null;
        }
        return timeCode.getCode();
    }

    @Override
    public TimeCode convertToEntityAttribute(Integer code) {
        if (code == null){
            return null;
        }
        return Stream.of(TimeCode.values())
                .filter(c -> c.getCode()==code)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
