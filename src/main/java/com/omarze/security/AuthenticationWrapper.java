package com.omarze.security;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Collections;

/**
 * created by julian
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthenticationWrapper {


    final Authentication authentication;


    public Collection<GrantedAuthority> getAuthorities() {
        return authentication == null ? Collections.EMPTY_LIST : ((User)authentication.getPrincipal()).getAuthorities();
    }


    public boolean hasAuthority(String authorityName) {
        Long existingAuthCount = getAuthorities()
            .stream().filter(a -> a.getAuthority().equalsIgnoreCase(authorityName)).count();

        return existingAuthCount > 0;
    }


    public static AuthenticationWrapper of(Authentication authentication) {
        return new AuthenticationWrapper(authentication);
    }


}

