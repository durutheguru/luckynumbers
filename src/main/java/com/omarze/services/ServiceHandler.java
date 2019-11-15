package com.omarze.services;


import com.omarze.exception.ServiceException;
import com.omarze.util.ValidatorUtil;

/**
 * created by julian
 */
public interface ServiceHandler<T> {

    T run() throws ServiceException;

}


