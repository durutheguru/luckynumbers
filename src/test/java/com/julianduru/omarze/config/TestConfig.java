package com.julianduru.omarze.config;


import com.github.javafaker.Faker;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.MySQLContainer;


/**
 * created by julian
 */
@TestConfiguration
public class TestConfig {


    @Bean
    public Faker faker() {
        return new Faker();
    }


    @Bean
    public MySQLContainer mySQLContainer() {
        MySQLContainer container = new MySQLContainer<>("")
            .withDatabaseName("");
    }


}
