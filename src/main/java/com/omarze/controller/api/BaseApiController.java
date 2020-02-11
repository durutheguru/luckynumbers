package com.omarze.controller.api;


import com.omarze.exception.ServiceException;
import com.omarze.util.MapperUtil;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.List;

/**
 * created by julian
 */
public class BaseApiController<D, P> {


    protected <T, R> R map(T t, Class<R> targetClass) {
        return MapperUtil.map(t, targetClass);
    }


    protected <T, R> Page<R> map(Page<T> tPage, Class<R> targetClass) {
        return MapperUtil.map(tPage, targetClass);
    }


    protected <T, R> Collection<R> map(Collection<T> tList, Class<R> targetClass) {
        return MapperUtil.map(tList, targetClass);
    }

    protected <T, R> List<R> map(List<T> tList, Class<R> targetClass) {
        return MapperUtil.map(tList, targetClass);
    }


    public D getById(Long id) throws ServiceException {
        return null;
    }


    public P getPage(Integer page, Integer size) throws ServiceException {
        return null;
    }


}
