package org.tec.multistore.mongo.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class LocalDateTimeReadConverter implements Converter<Date, LocalDateTime> {
    @Override
    public LocalDateTime convert(final Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}