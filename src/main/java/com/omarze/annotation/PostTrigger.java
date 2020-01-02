package com.omarze.annotation;


import com.omarze.event.ServiceEvent;

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

