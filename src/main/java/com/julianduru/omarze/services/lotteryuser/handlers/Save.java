package com.julianduru.omarze.services.lotteryuser.handlers;


import com.julianduru.util.MapperUtil;
import com.julianduru.omarze.api.dto.LotteryUserDTO;
import com.julianduru.omarze.entities.LotteryUser;
import com.julianduru.omarze.exception.ServiceException;
import com.julianduru.omarze.exception.UsernameAlreadyExistsException;
import com.julianduru.omarze.persistence.LotteryUserRepository;
import com.julianduru.omarze.security.UserDetailsServiceImpl;
import com.julianduru.omarze.services.CommandBase;
import lombok.Builder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotNull;

/**
 * created by julian
 */
public class Save extends CommandBase<LotteryUser> {

    @NotNull
    private LotteryUserDTO lotteryUserDTO;

    @NotNull
    private LotteryUserRepository userRepository;

    @NotNull
    private UserDetailsServiceImpl userDetailsService;

    @NotNull
    private PasswordEncoder passwordEncoder;


    @Builder
    private Save(LotteryUserDTO lotteryUserDTO, LotteryUserRepository userRepository, PasswordEncoder passwordEncoder, UserDetailsServiceImpl userDetailsService) {
        this.lotteryUserDTO = lotteryUserDTO;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }


    @Override
    public LotteryUser execute_() throws ServiceException {
        LotteryUser lotteryUser = MapperUtil.map(lotteryUserDTO, LotteryUser.class);
        lotteryUser.setPassword(passwordEncoder.encode(lotteryUserDTO.getPasswordIn()));

        return userRepository.save(lotteryUser);
    }


    @Override
    protected void validate() throws ServiceException {
        UserDetails userDetails = userDetailsService.loadUserByUsername(lotteryUserDTO.getUsername(), false);
        if (userDetails != null) {
            throw new UsernameAlreadyExistsException(lotteryUserDTO.getUsername());
        }
    }


}



