package com.omarze.services;


import com.julianduru.util.ValidatorUtil;
import com.omarze.exception.InvalidObjectException;
import com.omarze.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * created by julian
 */
public abstract class CommandBase<T> implements Command<T> {


    protected Logger logger = LoggerFactory.getLogger(CommandBase.class);


    protected void validate() throws ServiceException { }


    protected abstract T execute_() throws ServiceException;


    @Override
    public T execute() throws ServiceException {
        validateCommandState();
        validate();
        return execute_();
    }


    private void validateCommandState() throws InvalidObjectException {
        ValidatorUtil.validate(this);
    }


}

