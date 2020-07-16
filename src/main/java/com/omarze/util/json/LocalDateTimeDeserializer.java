package com.omarze.util.json;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.omarze.util.TimeUtil;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * created by julian
 */
public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {


    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return LocalDateTime.parse(p.getValueAsString(), TimeUtil.DEFAULT_DATE_TIME_FORMATTER);
    }


}
