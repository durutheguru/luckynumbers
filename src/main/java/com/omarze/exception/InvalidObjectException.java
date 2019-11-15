package com.omarze.exception;


/**
 * created by julian
 */
public class InvalidObjectException extends ServiceException {

    public final static Integer CODE = 1000002;

    public InvalidObjectException(String message) {
        super(message);
    }


    public InvalidObjectException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public Integer generateCode() {
        return CODE;
    }


}
