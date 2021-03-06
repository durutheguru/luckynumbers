package com.julianduru.omarze.services.backoffice.handlers;


import com.julianduru.util.MapperUtil;
import com.julianduru.omarze.api.dto.BackOfficeUserDTO;
import com.julianduru.omarze.entities.BackOfficeUser;
import com.julianduru.omarze.exception.ServiceException;
import com.julianduru.omarze.exception.UserAlreadyExistsException;
import com.julianduru.omarze.persistence.BackOfficeUserRepository;
import com.julianduru.omarze.services.CommandBase;
import lombok.Builder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.Optional;

/**
 * created by julian
 */
public class Save extends CommandBase<BackOfficeUser> {


    @NotNull
    private BackOfficeUserRepository backOfficeUserRepository;


    @NotNull
    private PasswordEncoder passwordEncoder;


    @NotNull(message = "Back-Office User is required")
    private BackOfficeUserDTO userDTO;


    @Builder
    public Save(
        BackOfficeUserRepository backOfficeUserRepository,
        PasswordEncoder passwordEncoder,
        BackOfficeUserDTO userDTO
    ) {
        this.backOfficeUserRepository = backOfficeUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDTO = userDTO;
    }


    @Override
    protected BackOfficeUser execute_() throws ServiceException {
        userExists();

        BackOfficeUser backOfficeUser = MapperUtil.map(userDTO, BackOfficeUser.class);
        backOfficeUser.setTimeAdded(ZonedDateTime.now());
        backOfficeUser.setPassword(passwordEncoder.encode(userDTO.getPasswordIn()));

        return backOfficeUserRepository.save(backOfficeUser);
    }


    private void userExists() throws UserAlreadyExistsException {
        String username = userDTO.getUsername();

        Optional<BackOfficeUser> existingUser = backOfficeUserRepository.findByUsername(username);
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException(username);
        }
    }


}




