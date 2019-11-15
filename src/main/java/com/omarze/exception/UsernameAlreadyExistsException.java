package com.omarze.exception;


/**
 * created by julian
 */
public class UsernameAlreadyExistsException extends ServiceException {


    public final static Integer CODE = 1000622;

    public UsernameAlreadyExistsException(String username) {
        super(String.format("Username '%s' already exists", username));
    }


    @Override
    public Integer generateCode() {
        return CODE;
    }



}
