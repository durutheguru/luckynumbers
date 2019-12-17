package com.omarze.util;


import org.modelmapper.ModelMapper;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * created by julian
 */
@Component
public class MapperUtil implements ApplicationContextAware {


    protected static ModelMapper modelMapper;


    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        modelMapper = context.getBean(ModelMapper.class);
    }


    public static <T, R> R map(T t, Class<R> targetClass) {
        return t == null ? null : modelMapper.map(t, targetClass);
    }


    public static <T, R> Page<R> map(Page<T> tPage, Class<R> targetClass) {
        return tPage == null ? Page.<R>empty() : tPage.map(t -> modelMapper.map(t, targetClass));
    }


    public static <T, R> List<R> map(List<T> tList, Class<R> targetClass) {
        return tList == null ? new ArrayList<>() : tList.stream().map(t -> modelMapper.map(t, targetClass))
                .collect(Collectors.toList());
    }


}


