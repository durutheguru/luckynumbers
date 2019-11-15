package com.omarze.exception;


/**
 * created by julian
 */
public class EntityNotFoundException extends ServiceException {


    public final static Integer CODE = 1000442;


    public EntityNotFoundException(String entityName, Long id) {
        super(String.format("%s ID %d was not found.", entityName, id));
    }


    @Override
    public Integer generateCode() {
        return CODE;
    }


}
