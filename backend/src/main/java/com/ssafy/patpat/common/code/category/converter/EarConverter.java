package com.ssafy.patpat.common.code.category.converter;

import com.ssafy.patpat.common.code.category.Cloth;
import com.ssafy.patpat.common.code.category.Ear;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class EarConverter implements AttributeConverter<Ear, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Ear ear) {
        if (ear == null){
            return null;
        }
        return ear.getCode();
    }

    @Override
    public Ear convertToEntityAttribute(Integer code) {
        if (code == null){
            return null;
        }
        return Stream.of(Ear.values())
                .filter(c -> c.getCode()==code)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
