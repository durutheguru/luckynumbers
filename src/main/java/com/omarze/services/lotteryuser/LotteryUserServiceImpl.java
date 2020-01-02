package com.omarze.services.lotteryuser;


import com.omarze.api.dto.LotteryUserDTO;
import com.omarze.entities.LotteryUser;
import com.omarze.exception.ServiceException;
import com.omarze.persistence.LotteryUserRepository;
import com.omarze.security.UserDetailsServiceImpl;
import com.omarze.services.lotteryuser.handlers.Save;
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

