package com.julianduru.omarze.data.backoffice;


import com.github.javafaker.Faker;
import com.julianduru.omarze.entities.BackOfficeUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * created by julian
 */
@Component
@RequiredArgsConstructor
public class BackOfficeUserDataProvider {


    final Faker faker;


    public BackOfficeUser newEntity() {
        BackOfficeUser user = new BackOfficeUser();

        user.setName(faker.name().fullName());
        user.setPassword(faker.internet().password());
        user.setUsername(faker.code().isbn10());
        user.setEmail(faker.internet().emailAddress());

        return user;
    }


}
