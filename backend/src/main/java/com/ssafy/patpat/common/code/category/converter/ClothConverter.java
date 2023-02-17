package com.ssafy.patpat.common.code.category.converter;

import com.ssafy.patpat.common.code.category.Cloth;
import com.ssafy.patpat.common.code.category.Gender;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class ClothConverter implements AttributeConverter<Cloth, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Cloth cloth) {
        if (cloth == null){
            return null;
        }
        return cloth.getCode();
    }

    @Override
    public Cloth convertToEntityAttribute(Integer code) {
        if (code == null){
            return null;
        }
        return Stream.of(Cloth.values())
                .filter(c -> c.getCode()==code)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
