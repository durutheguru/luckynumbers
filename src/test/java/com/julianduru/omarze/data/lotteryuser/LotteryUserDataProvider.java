package com.julianduru.omarze.data.lotteryuser;


import com.github.javafaker.Faker;
import com.julianduru.omarze.api.dto.LotteryUserDTO;
import com.julianduru.omarze.entities.LotteryUser;
import com.julianduru.omarze.persistence.LotteryUserRepository;
import com.julianduru.util.NullAwareBeanUtils;
import com.julianduru.util.test.JpaDataProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * created by julian
 */
@Component
@RequiredArgsConstructor
public class LotteryUserDataProvider implements JpaDataProvider<LotteryUser> {

    private final Faker faker;

    private final PasswordEncoder passwordEncoder;

    private final LotteryUserRepository lotteryUserRepository;


    public LotteryUser newLotteryUser() {
        return provide();
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


    public LotteryUser saveLotteryUser(LotteryUser sample) {
        LotteryUser lotteryUser = newLotteryUser();
        lotteryUser.setPassword(passwordEncoder.encode(lotteryUser.getPassword()));

        NullAwareBeanUtils.copy(sample, lotteryUser);

        return lotteryUserRepository.save(lotteryUser);
    }


    public List<LotteryUser> saveLotteryUsers(int count) {
        List<LotteryUser> users = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            users.add(saveLotteryUser());
        }

        return users;
    }


    @Override
    public JpaRepository<LotteryUser, Long> getRepository() {
        return lotteryUserRepository;
    }


    @Override
    public LotteryUser provide() {
        LotteryUser user = new LotteryUser();

        user.setName(faker.name().fullName());
        user.setUsername(faker.internet().emailAddress());
        user.setPassword(faker.internet().password());
        user.setEmail(user.getUsername());
        user.setPhoneNumber(faker.phoneNumber().cellPhone());

        return user;
    }


    @Override
    public LotteryUser provide(LotteryUser sample) {
        LotteryUser user = provide();
        NullAwareBeanUtils.copy(sample, user);

        return user;
    }


}
