package com.omarze.services;


import com.omarze.exception.ServiceException;

/**
 * created by julian
 */
public interface ServiceHandler<T> {

    T run() throws ServiceException;

}

