package com.omarze.exception;


/**
 * created by julian
 */
public class InvalidUpdateException extends ServiceException {

    public final static Integer CODE = 1000092;

    public InvalidUpdateException(String message) {
        super(message);
    }


    public InvalidUpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public Integer generateCode() {
        return CODE;
    }


}
