package com.omarze.exception;


/**
 * created by julian
 */
public class UserNotFoundException extends ServiceException {


    public final static Integer CODE = 1000422;

    public UserNotFoundException(String username) {
        super(String.format("User was not found. %s", username));
    }


    @Override
    public Integer generateCode() {
        return CODE;
    }



}
