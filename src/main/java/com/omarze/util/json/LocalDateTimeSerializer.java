package com.omarze.util.json;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.omarze.util.TimeUtil;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * created by julian
 */
public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {


    @Override
    public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(value.format(TimeUtil.DEFAULT_DATE_TIME_FORMATTER));
    }

}
