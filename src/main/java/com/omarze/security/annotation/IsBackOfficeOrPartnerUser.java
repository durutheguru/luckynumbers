package com.omarze.security.annotation;


import com.omarze.entities.BackOfficeUser;
import com.omarze.entities.PartnerUser;
import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * created by julian
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@PreAuthorize(
    "hasAnyAuthority('" + BackOfficeUser.ROLE_ID + "', '" + PartnerUser.ROLE_ID + "')"
)
public @interface IsBackOfficeOrPartnerUser {

}
