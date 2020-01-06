package com.omarze.util;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.util.Assert;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * created by julian
 */
public class ObjectUtil {


    public static void copyProperties(Object src, Object dest) {
        Assert.notNull(src, "Source Object is required");
        Assert.notNull(src, "Destination Object is required");

        List<String> emptyFields = getNullFields(src);
        BeanUtils.copyProperties(src, dest, emptyFields.toArray(new String[emptyFields.size()]));
    }


    public static List<String> getNullFields(Object obj) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(obj);
        return Stream.of(beanWrapper.getPropertyDescriptors())
                .map(PropertyDescriptor::getName)
                .filter(n -> {
                    try {
                        return beanWrapper.getPropertyValue(n) == null;
                    } catch (InvalidPropertyException e) {
                        return true;
                    }
                })
                .collect(Collectors.toList());
    }


}
