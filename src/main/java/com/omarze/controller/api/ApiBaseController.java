package com.omarze.controller.api;


import com.omarze.util.MapperUtil;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * created by julian
 */
public abstract  class ApiBaseController {


    protected <T, R> R map(T t, Class<R> targetClass) {
        return MapperUtil.map(t, targetClass);
    }


    protected <T, R> Page<R> map(Page<T> tPage, Class<R> targetClass) {
        return MapperUtil.map(tPage, targetClass);
    }


    protected <T, R> List<R> map(List<T> tList, Class<R> targetClass) {
        return MapperUtil.map(tList, targetClass);
    }


}
