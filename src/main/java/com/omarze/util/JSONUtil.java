package com.omarze.util;


import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * created by julian
 */
public class JSONUtil {


    public static String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
