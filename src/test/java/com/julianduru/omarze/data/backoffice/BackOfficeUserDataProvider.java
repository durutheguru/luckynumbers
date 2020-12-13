package com.julianduru.omarze.data.backoffice;


import com.github.javafaker.Faker;
import com.julianduru.omarze.entities.BackOfficeUser;
import com.julianduru.omarze.persistence.BackOfficeUserRepository;
import com.julianduru.util.NullAwareBeanUtils;
import com.julianduru.util.test.DataProvider;
import com.julianduru.util.test.JpaDataProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * created by julian
 */
@Component
@RequiredArgsConstructor
public class BackOfficeUserDataProvider implements JpaDataProvider<BackOfficeUser> {


    final Faker faker;

    final BackOfficeUserRepository userRepository;


    public BackOfficeUser newEntity() {
        return provide();
    }


    @Override
    public JpaRepository<BackOfficeUser, Long> getRepository() {
        return userRepository;
    }


    @Override
    public BackOfficeUser provide() {
        BackOfficeUser user = new BackOfficeUser();

        user.setName(faker.name().fullName());
        user.setPassword(faker.internet().password());
        user.setUsername(faker.code().isbn10());
        user.setEmail(faker.internet().emailAddress());

        return user;
    }


    @Override
    public BackOfficeUser provide(BackOfficeUser sample) {
        BackOfficeUser user = provide();
        NullAwareBeanUtils.copy(sample, user);

        return user;
    }


}
