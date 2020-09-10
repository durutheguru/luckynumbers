package com.julianduru.omarze.exception;


/**
 * created by julian
 */
public class InvalidObjectException extends ServiceException {


    public InvalidObjectException(String message) {
        super(message);
    }


    public InvalidObjectException(String message, Throwable cause) {
        super(message, cause);
    }


}
