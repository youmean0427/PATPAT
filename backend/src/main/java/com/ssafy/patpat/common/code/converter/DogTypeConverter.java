package com.ssafy.patpat.common.code.converter;

import com.ssafy.patpat.common.code.DogType;
import com.ssafy.patpat.common.code.MissingState;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class DogTypeConverter implements AttributeConverter<DogType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(DogType dogType) {
        if (dogType == null){
            return null;
        }
        return dogType.getCode();
    }

    @Override
    public DogType convertToEntityAttribute(Integer code) {
        if (code == null){
            return null;
        }
        return Stream.of(DogType.values())
                .filter(c -> c.getCode()==code)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
