package com.omarze.security;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * created by julian
 */
public class Auth {


    public static Authentication getContext() {
        return SecurityContextHolder.getContext().getAuthentication();
    }


    public static void setContext(Authentication auth) {
        SecurityContextHolder.getContext().setAuthentication(auth);
    }


}

