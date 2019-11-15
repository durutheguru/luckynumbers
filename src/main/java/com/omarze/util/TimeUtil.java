package com.omarze.util;


import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * created by julian
 */
public class TimeUtil {


    public static Date ldtToDate(LocalDateTime ldt) {
        Assert.notNull(ldt, "LocalDateTime argument cannot be null");
        return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
    }


}
