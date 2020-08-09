package com.julianduru.omarze.exception;


/**
 * created by julian
 */
public class InvalidUpdateException extends ServiceException {


    public InvalidUpdateException(String message) {
        super(message);
    }


    public InvalidUpdateException(String message, Throwable cause) {
        super(message, cause);
    }


}
