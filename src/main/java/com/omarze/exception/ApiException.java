package com.omarze.exception;


/**
 * created by julian
 */
public class ApiException extends ServiceException {

    private final static Integer CODE = 1000001;


    public ApiException() {
        super();
    }


    public ApiException(String message) {
        super(message);
    }


    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }


    public ApiException(Throwable cause) {
        super(cause);
    }

    @Override
    public Integer generateCode() {
        return CODE;
    }


}
