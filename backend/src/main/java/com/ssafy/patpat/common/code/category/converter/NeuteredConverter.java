package com.ssafy.patpat.common.code.category.converter;

import com.ssafy.patpat.common.code.category.Color;
import com.ssafy.patpat.common.code.category.Neutered;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class NeuteredConverter implements AttributeConverter<Neutered, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Neutered neutered) {
        if (neutered == null){
            return null;
        }
        return neutered.getCode();
    }

    @Override
    public Neutered convertToEntityAttribute(Integer code) {
        if (code == null){
            return null;
        }
        return Stream.of(Neutered.values())
                .filter(c -> c.getCode()==code)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
