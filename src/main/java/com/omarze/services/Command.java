package com.omarze.services;


import com.omarze.exception.ServiceException;

/**
 * created by julian
 */
public interface Command<T> {

    T execute() throws ServiceException;

}


