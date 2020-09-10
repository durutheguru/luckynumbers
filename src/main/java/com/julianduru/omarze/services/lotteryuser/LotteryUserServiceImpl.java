package com.julianduru.omarze.services.lotteryuser;


import com.julianduru.omarze.api.dto.LotteryUserDTO;
import com.julianduru.omarze.entities.LotteryUser;
import com.julianduru.omarze.exception.ServiceException;
import com.julianduru.omarze.persistence.LotteryUserRepository;
import com.julianduru.omarze.security.UserDetailsServiceImpl;
import com.julianduru.omarze.services.lotteryuser.handlers.Save;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * created by julian
 */
@Service
@RequiredArgsConstructor
public class LotteryUserServiceImpl implements LotteryUserService {


    private final LotteryUserRepository lotteryUserRepository;


    private final UserDetailsServiceImpl userDetailsService;


    private final PasswordEncoder passwordEncoder;



    public LotteryUser addLotteryUser(LotteryUserDTO lotteryUser) throws ServiceException {
        return Save.builder()
                .lotteryUserDTO(lotteryUser)
                .userRepository(lotteryUserRepository)
                .passwordEncoder(passwordEncoder)
                .userDetailsService(userDetailsService)
                .build()
                .execute();
    }


}

