package com.julianduru.omarze.exception;


/**
 * created by julian
 */
public class DuplicatePartnerDescriptionException extends ServiceException {


    public DuplicatePartnerDescriptionException() {
        super("Partner description already exists");
    }


}
