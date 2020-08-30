package com.julianduru.omarze.security;


import com.julianduru.omarze.entities.BackOfficeUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;

/**
 * created by julian
 */
public class AuthContext {


    public static Authentication backOfficeUser() {
        return new UsernamePasswordAuthenticationToken(
            "user", "", Collections.singletonList(new SimpleGrantedAuthority(BackOfficeUser.ROLE_ID))
        );
    }


}
