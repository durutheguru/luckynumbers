package com.julianduru.omarze.exception;


/**
 * created by julian
 */
public class UserNotFoundException extends ServiceException {


    public UserNotFoundException(String username) {
        super(String.format("User was not found. %s", username));
    }


}
