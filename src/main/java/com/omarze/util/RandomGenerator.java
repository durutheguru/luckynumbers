package com.omarze.util;


import com.github.javafaker.Faker;
import com.github.javafaker.Internet;
import com.github.javafaker.Name;
import com.github.javafaker.PhoneNumber;

import java.util.UUID;


/**
 * created by julian
 */
public class RandomGenerator {


    public static Faker faker = new Faker();


    public static String randomString(int length) {
        String output = "";

        for (int i = 0; i < length; i++) {
            output += (char) (65 + faker.random().nextInt(26));
        }

        return output;
    }


    public static String randomStringNumber(int length) {
        String output = "";

        for (int i = 0; i < length; i++) {
            output += faker.random().nextInt(10);
        }

        return output;
    }


    public static int randomInteger(int limit) {
        return faker.random().nextInt(limit);
    }


    public static Name name() {
        return faker.name();
    }


    public static Internet internet() {
        return faker.internet();
    }


    public static PhoneNumber phoneNumber() {
        return faker.phoneNumber();
    }


    public static String uuid() {
        return UUID.randomUUID().toString();
    }


}
