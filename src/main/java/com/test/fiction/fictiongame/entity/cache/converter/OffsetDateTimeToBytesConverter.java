package com.test.fiction.fictiongame.entity.cache.converter;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;

@Component
@WritingConverter
public class OffsetDateTimeToBytesConverter implements Converter<OffsetDateTime, byte[]>{

	@Override
    public byte[] convert(final OffsetDateTime source) {
        return source.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME).getBytes();
    }

}
