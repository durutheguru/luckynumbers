package com.omarze.services.user;


import com.omarze.Constants;
import com.omarze.entities.LotteryUser;
import com.omarze.persistence.LotteryUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * created by julian
 */
@Component
public class LotteryUserDetailsProvider implements UserDetailsProvider<LotteryUser> {


    private final LotteryUserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    public LotteryUserDetailsProvider(LotteryUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public Optional<LotteryUser> findUser(String principal) {
        return userRepository.findByUsername(principal);
    }


    @Override
    public Optional<LotteryUser> findUser(String principal, String credentials) {
        Optional<LotteryUser> user = userRepository.findByUsername(principal);
        if (user.isPresent() && passwordEncoder.matches(credentials, user.get().getPassword())){
            return user;
        }

        return Optional.empty();
    }


    @Override
    public String name() {
        return Constants.Users.LOTTERY_USER;
    }


}
