package com.ssafy.patpat.common.code.category.converter;

import com.ssafy.patpat.common.code.category.Cloth;
import com.ssafy.patpat.common.code.category.Tail;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class TailConverter implements AttributeConverter<Tail, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Tail tail) {
        if (tail == null){
            return null;
        }
        return tail.getCode();
    }

    @Override
    public Tail convertToEntityAttribute(Integer code) {
        if (code == null){
            return null;
        }
        return Stream.of(Tail.values())
                .filter(c -> c.getCode()==code)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
