package com.ssafy.patpat.common.code.category.converter;

import com.ssafy.patpat.common.code.category.Color;
import com.ssafy.patpat.common.code.category.Gender;

import javax.persistence.AttributeConverter;
import java.util.stream.Stream;

public class ColorConverter implements AttributeConverter<Color, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Color color) {
        if (color == null){
            return null;
        }
        return color.getCode();
    }

    @Override
    public Color convertToEntityAttribute(Integer code) {
        if (code == null){
            return null;
        }
        return Stream.of(Color.values())
                .filter(c -> c.getCode()==code)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
