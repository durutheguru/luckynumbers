package com.julianduru.omarze.security;


import com.julianduru.omarze.entities.ApplicationUser;
import com.julianduru.omarze.exception.ServiceException;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * created by julian
 */
public interface IUserDetailsService extends UserDetailsService {


    boolean usernameExists(String username);


    boolean emailExists(String email);


    default void validateUserDetailsNotExists(ApplicationUser user) throws ServiceException {
        if (usernameExists(user.getUsername())) {
            throw new ServiceException(String.format("Username '%s' already exists", user.getUsername()));
        }

        if (emailExists(user.getEmail())) {
            throw new ServiceException(String.format("Email '%s' already exists", user.getEmail()));
        }
    }


}
