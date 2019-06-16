package com.omarze.services;


import com.omarze.exception.ServiceException;

/**
 * created by julian
 */
public interface ServiceHandler<T, C extends Components> {

    T runWith(C components) throws ServiceException;

}

