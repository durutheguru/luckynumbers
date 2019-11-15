package com.omarze.util;



import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import java.sql.Timestamp;
import java.time.LocalDateTime;


/**
 * created by julian
 */
@Converter
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Timestamp> {


    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime localDateTime) {
        return localDateTime == null ? null : Timestamp.valueOf(localDateTime);
    }


    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp dbData) {
        return dbData == null ? null : dbData.toLocalDateTime();
    }


}
