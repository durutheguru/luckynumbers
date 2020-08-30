package com.julianduru.omarze.exception;


/**
 * created by julian
 */
public class UsernameAlreadyExistsException extends ServiceException {


    public UsernameAlreadyExistsException(String username) {
        super(String.format("Username '%s' already exists", username));
    }


}
