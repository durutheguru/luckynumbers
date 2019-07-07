package com.omarze.exception;


/**
 * created by julian
 */
public class NoUserProviderFoundException extends ServiceException {


    public final static Integer CODE = 1004701;

    public NoUserProviderFoundException() {
        super("No User Details Provider was found");
    }


    @Override
    public Integer generateCode() {
        return CODE;
    }


}
