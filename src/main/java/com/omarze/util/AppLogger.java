package com.omarze.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * created by julian
 */
public class AppLogger {


    private static Logger logger = LoggerFactory.getLogger(AppLogger.class);


    public static void error(Throwable throwable) {
        error(throwable.getMessage(), throwable);
    }


    public static void error(String message, Throwable throwable) {
        logger.error(message, throwable);
    }


    public static void info(String message) {
        logger.info(message);
    }


}
