package com.julianduru.omarze.services;


import com.julianduru.omarze.exception.ServiceException;

/**
 * created by julian
 */
public interface Command<T> {

    T execute() throws ServiceException;

}


