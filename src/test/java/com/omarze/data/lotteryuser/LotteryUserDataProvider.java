package com.omarze.data.lotteryuser;


import com.github.javafaker.Faker;
import com.omarze.api.dto.LotteryUserDTO;
import com.omarze.entities.LotteryUser;
import com.omarze.persistence.LotteryUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * created by julian
 */
@Component
public class LotteryUserDataProvider {

    private final Faker faker;

    private final PasswordEncoder passwordEncoder;

    private final LotteryUserRepository lotteryUserRepository;


    public LotteryUserDataProvider(Faker faker, PasswordEncoder passwordEncoder, LotteryUserRepository lotteryUserRepository) {
        this.faker = faker;
        this.passwordEncoder = passwordEncoder;
        this.lotteryUserRepository = lotteryUserRepository;
    }


    public LotteryUser newLotteryUser() {
        LotteryUser user = new LotteryUser();

        user.setName(faker.name().fullName());
        user.setUsername(faker.internet().emailAddress());
        user.setPassword(faker.internet().password());
        user.setEmail(user.getUsername());
        user.setPhoneNumber(faker.phoneNumber().cellPhone());


        return user;
    }


    public LotteryUserDTO newLotteryUserDTO() {
        LotteryUserDTO user = new LotteryUserDTO();

        user.setName(faker.name().fullName());
        user.setUsername(faker.internet().emailAddress());
        user.setPasswordIn(faker.internet().password());
        user.setEmail(user.getUsername());
        user.setPhoneNumber(faker.phoneNumber().cellPhone());


        return user;
    }


    public LotteryUser saveLotteryUser() {
        LotteryUser lotteryUser = newLotteryUser();
        lotteryUser.setPassword(passwordEncoder.encode(lotteryUser.getPassword()));
        return lotteryUserRepository.save(lotteryUser);
    }


    public List<LotteryUser> saveLotteryUsers(int count) {
        List<LotteryUser> users = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            users.add(saveLotteryUser());
        }

        return users;
    }


}
