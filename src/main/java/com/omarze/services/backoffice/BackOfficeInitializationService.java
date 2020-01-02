package com.omarze.services.backoffice;


import com.omarze.api.dto.BackOfficeUserDTO;
import com.omarze.exception.ServiceException;
import com.omarze.util.AppLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * created by julian
 */
@Service
@RequiredArgsConstructor
public class BackOfficeInitializationService {


    private final BackOfficeUserService userService;


    @EventListener({ApplicationReadyEvent.class})
    public void handleApplicationStarted() {
        try {
            if (!userService.backOfficeUserExists()) {
                userService.saveBackOfficeUser(newFirstUser());
            }
        }
        catch (ServiceException e) {
            AppLogger.error(e);
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




