package com.julianduru.omarze.exception;


/**
 * created by julian
 */
public class UserAlreadyExistsException extends ServiceException {


    public UserAlreadyExistsException(String username) {
        super(String.format("Username %s already exists", username));
    }


}
