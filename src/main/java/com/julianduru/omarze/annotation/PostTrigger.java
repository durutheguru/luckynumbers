package com.julianduru.omarze.annotation;


import com.julianduru.omarze.event.ServiceEvent;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * created by julian
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PostTrigger {


    Class<? extends ServiceEvent>[] value();


}

