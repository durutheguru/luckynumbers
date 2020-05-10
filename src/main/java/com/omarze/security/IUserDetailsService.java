package com.omarze.security;


import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * created by julian
 */
public interface IUserDetailsService extends UserDetailsService {


    boolean usernameExists(String username);


    boolean emailExists(String email);


}
