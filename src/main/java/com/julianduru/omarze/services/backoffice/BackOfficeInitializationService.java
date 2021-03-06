package com.julianduru.omarze.services.backoffice;


import com.julianduru.security.Auth;
import com.julianduru.omarze.api.dto.BackOfficeUserDTO;
import com.julianduru.omarze.exception.ServiceException;
import com.julianduru.omarze.security.AuthContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * created by julian
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BackOfficeInitializationService {


    private final BackOfficeUserService userService;


    @EventListener({ApplicationReadyEvent.class})
    public void handleApplicationStarted() {
        try {
            Auth.setContext(AuthContext.backOfficeUser());

            if (!userService.backOfficeUserExists()) {
                userService.saveBackOfficeUser(newFirstUser());
            }
        }
        catch (ServiceException e) {
            log.error(e.getMessage(), e);
        }
    }


    private BackOfficeUserDTO newFirstUser() {
        BackOfficeUserDTO userDTO = new BackOfficeUserDTO();

        userDTO.setUsername("superuser");
        userDTO.setPasswordIn("password");
        userDTO.setName("Julian Duru");
        userDTO.setEmail("durutheguru@gmail.com");
        userDTO.setTimeAdded(LocalDateTime.now());

        return userDTO;
    }





}




