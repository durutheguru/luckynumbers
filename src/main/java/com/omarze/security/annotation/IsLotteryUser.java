package com.omarze.security.annotation;

import com.omarze.entities.LotteryUser;
import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * created by julian
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@PreAuthorize("hasAuthority('" + LotteryUser.ROLE_ID + "')")
public @interface IsLotteryUser {

}




