package com.omarze.security;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * created by julian
 */
public class UsernameAuthenticationToken extends UsernamePasswordAuthenticationToken {


    public UsernameAuthenticationToken(Object principal) {
        super(principal, null);
    }


}
