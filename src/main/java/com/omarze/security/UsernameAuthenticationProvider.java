package com.omarze.security;


import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * created by julian
 */
@Component
public class UsernameAuthenticationProvider implements AuthenticationProvider {


    private final UserDetailsServiceImpl userDetailsService;


    public UsernameAuthenticationProvider(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String user = authentication.getPrincipal().toString();

        UserDetails userDetails = userDetailsService.loadUserByUsername(user);

        if (userDetails == null) {
            throw new UsernameNotFoundException(String.format("Username %s not found", user));
        }

        return new UsernameAuthenticationToken(user);
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernameAuthenticationToken.class);
    }



}
