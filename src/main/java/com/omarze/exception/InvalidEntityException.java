package com.omarze.exception;


/**
 * created by julian
 */
public class InvalidEntityException extends ServiceException {

    public final static Integer CODE = 1000002;

    public InvalidEntityException(String message) {
        super(message);
    }


    public InvalidEntityException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public Integer generateCode() {
        return CODE;
    }


}
