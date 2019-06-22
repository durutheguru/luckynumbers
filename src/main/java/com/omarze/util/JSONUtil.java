package com.omarze.util;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * created by julian
 */
public class JSONUtil {

    static ObjectMapper objectMapper = new ObjectMapper();


    public static String asJsonString(Object obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }


    public static <T> T fromJsonString(String json, Class<T> klass) throws IOException {
        return objectMapper.readValue(json, klass);
    }


}
