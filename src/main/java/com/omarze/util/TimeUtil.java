package com.omarze.util;


import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * created by julian
 */
public class TimeUtil {


    public final static String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";


    public final static String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";


    public final static DateTimeFormatter DEFAULT_DATE_FORMATTER = DateTimeFormatter.ofPattern(
        DEFAULT_DATE_FORMAT
    );


    public final static DateTimeFormatter DEFAULT_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(
        DEFAULT_DATE_TIME_FORMAT
    );



    public static Date ldtToDate(LocalDateTime ldt) {
        Assert.notNull(ldt, "LocalDateTime argument cannot be null");
        return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
    }


}
