package com.omarze.persistence.handlers;


import com.omarze.entities.ApplicationUser;
import com.omarze.exception.ServiceException;
import com.omarze.security.IUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * created by julian
 */
public class AbstractApplicationUserHandler {


    @Autowired
    IUserDetailsService userDetailsService;


    @Autowired
    PasswordEncoder passwordEncoder;



    protected <T extends ApplicationUser> void prepareApplicationUser(T user) throws ServiceException {
        userDetailsService.validateUserDetailsNotExists(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }


}
