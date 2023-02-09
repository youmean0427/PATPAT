package com.ssafy.patpat.common.code.converter;

import com.ssafy.patpat.common.code.BoardCode;
import com.ssafy.patpat.common.code.TimeCode;

import javax.persistence.AttributeConverter;
import java.util.stream.Stream;

public class BoardCodeConverter implements AttributeConverter<BoardCode, Integer> {
    @Override
    public Integer convertToDatabaseColumn(BoardCode boardCode) {
        if (boardCode == null){
            return null;
        }
        return boardCode.getCode();
    }

    @Override
    public BoardCode convertToEntityAttribute(Integer code) {
        if (code == null){
            return null;
        }
        return Stream.of(BoardCode.values())
                .filter(c -> c.getCode()==code)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
