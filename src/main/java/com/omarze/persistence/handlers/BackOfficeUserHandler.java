package com.omarze.persistence.handlers;


import com.omarze.entities.BackOfficeUser;
import com.omarze.exception.ServiceException;
import com.omarze.exception.UsernameAlreadyExistsException;
import com.omarze.security.IUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * created by julian
 */
@Component
@RepositoryEventHandler
@RequiredArgsConstructor
public class BackOfficeUserHandler {


    final IUserDetailsService userDetailsService;


    final PasswordEncoder passwordEncoder;


    @HandleBeforeCreate
    public void handleBeforeCreate(BackOfficeUser user) throws ServiceException {
        if (userDetailsService.usernameExists(user.getUsername())) {
            throw new ServiceException(String.format("Username %s already exists", user.getUsername()));
        }

        if (userDetailsService.emailExists(user.getEmail())) {
            throw new ServiceException(String.format("Email %s already exists", user.getEmail()));
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }



}

