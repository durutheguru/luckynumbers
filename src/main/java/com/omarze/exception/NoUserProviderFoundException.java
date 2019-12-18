package com.omarze.exception;


/**
 * created by julian
 */
public class NoUserProviderFoundException extends ServiceException {


    public NoUserProviderFoundException() {
        super("No User Details Provider was found");
    }



}
