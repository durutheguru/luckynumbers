package com.omarze.services.lotteryuser.handlers;


import com.omarze.entities.LotteryUser;
import com.omarze.exception.InvalidObjectException;
import com.omarze.exception.ServiceException;
import com.omarze.exception.UsernameAlreadyExistsException;
import com.omarze.persistence.LotteryUserRepository;
import com.omarze.security.UserDetailsServiceImpl;
import com.omarze.services.Command;
import com.omarze.util.ValidatorUtil;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * created by julian
 */
public class AddLotteryUser implements Command<LotteryUser> {

    private final LotteryUser lotteryUser;

    private final LotteryUserRepository userRepository;

    private final UserDetailsServiceImpl userDetailsService;

    private final PasswordEncoder passwordEncoder;


    public AddLotteryUser(LotteryUser lotteryUser, LotteryUserRepository userRepository, PasswordEncoder passwordEncoder, UserDetailsServiceImpl userDetailsService) {
        this.lotteryUser = lotteryUser;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }


    @Override
    public LotteryUser execute() throws ServiceException {
        validateNewUser();
        verifyUsernameAvailable();

        lotteryUser.setPassword(passwordEncoder.encode(lotteryUser.getPassword()));

        return userRepository.save(lotteryUser);
    }


    private void validateNewUser() throws InvalidObjectException {
        ValidatorUtil.validate(lotteryUser);
    }


    private void verifyUsernameAvailable() throws UsernameAlreadyExistsException {
        UserDetails userDetails = userDetailsService.loadUserByUsername(lotteryUser.getUsername(), false);
        if (userDetails != null) {
            throw new UsernameAlreadyExistsException(lotteryUser.getUsername());
        }
    }


}



