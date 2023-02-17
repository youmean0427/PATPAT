package com.ssafy.patpat.common.code.converter;

import com.ssafy.patpat.common.code.MsgCode;
import com.ssafy.patpat.common.code.ProtectState;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class MsgCodeConverter implements AttributeConverter<MsgCode, Integer> {

    @Override
    public Integer convertToDatabaseColumn(MsgCode msgCode) {
        if (msgCode == null){
            return null;
        }
        return msgCode.getCode();
    }

    @Override
    public MsgCode convertToEntityAttribute(Integer code) {
        if (code == null){
            return null;
        }
        return Stream.of(MsgCode.values())
                .filter(c -> c.getCode()==code)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
