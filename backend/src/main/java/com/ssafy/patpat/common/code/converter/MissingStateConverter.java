package com.ssafy.patpat.common.code.converter;

import com.ssafy.patpat.common.code.MissingState;
import com.ssafy.patpat.common.code.ProtectState;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class MissingStateConverter implements AttributeConverter<MissingState, Integer> {
    @Override
    public Integer convertToDatabaseColumn(MissingState missingState) {
        if (missingState == null){
            return null;
        }
        return missingState.getCode();
    }

    @Override
    public MissingState convertToEntityAttribute(Integer code) {
        if (code == null){
            return null;
        }
        return Stream.of(MissingState.values())
                .filter(c -> c.getCode()==code)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
