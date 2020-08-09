package com.julianduru.omarze.exception;


/**
 * created by julian
 */
public class EntityNotFoundException extends ServiceException {


    public EntityNotFoundException(String entityName, Long id) {
        super(String.format("%s ID %d was not found.", entityName, id));
    }


}
