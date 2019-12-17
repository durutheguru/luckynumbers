package com.omarze.security.annotation;


import com.omarze.entities.BackOfficeUser;
import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@PreAuthorize("hasAuthority('" + BackOfficeUser.ROLE_ID + "')")
public @interface IsBackOfficeUser {

}
