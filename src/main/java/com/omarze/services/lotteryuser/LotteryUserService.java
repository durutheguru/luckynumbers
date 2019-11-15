package com.omarze.services.lotteryuser;


import com.omarze.entities.LotteryUser;
import com.omarze.exception.ServiceException;
import com.omarze.persistence.LotteryUserRepository;
import com.omarze.security.UserDetailsServiceImpl;
import com.omarze.services.lotteryuser.handlers.AddLotteryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * created by julian
 */
@Service
public class LotteryUserService {

    private final LotteryUserRepository lotteryUserRepository;

    private final UserDetailsServiceImpl userDetailsService;

    private final PasswordEncoder passwordEncoder;


    public LotteryUserService(LotteryUserRepository lotteryUserRepository, UserDetailsServiceImpl userDetailsService, PasswordEncoder passwordEncoder) {
        this.lotteryUserRepository = lotteryUserRepository;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }


    public LotteryUser addLotteryUser(LotteryUser lotteryUser) throws ServiceException {
        return new AddLotteryUser(lotteryUser, lotteryUserRepository, passwordEncoder, userDetailsService).run();
    }


}
