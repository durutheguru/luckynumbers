package com.omarze.services;


import com.omarze.exception.ServiceException;
import com.omarze.util.ValidatorUtil;

/**
 * created by julian
 */
public abstract class AbstractServiceHandler<T> implements ServiceHandler<T> {


    @Override
    public T run() throws ServiceException {
        beforeExecute();
        T result = execute();
        afterExecute();

        return result;
    }


    protected void beforeExecute() throws ServiceException {
        ValidatorUtil.validate(this);
        preExecute();
    }


    protected void afterExecute() throws ServiceException {}


    protected void preExecute() throws ServiceException {}


    protected abstract T execute() throws ServiceException;


}
