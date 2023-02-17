package com.ssafy.patpat.common.code.category.converter;

import com.ssafy.patpat.common.code.category.Cloth;
import com.ssafy.patpat.common.code.category.Pattern;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class PatternConverter implements AttributeConverter<Pattern, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Pattern pattern) {
        if (pattern == null){
            return null;
        }
        return pattern.getCode();
    }

    @Override
    public Pattern convertToEntityAttribute(Integer code) {
        if (code == null){
            return null;
        }
        return Stream.of(Pattern.values())
                .filter(c -> c.getCode()==code)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
