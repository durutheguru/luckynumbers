package com.julianduru.omarze.config;


import com.julianduru.omarze.controller.BaseControllerTest;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;

import javax.sql.DataSource;

/**
 * created by julian
 */
@TestConfiguration
public class TestDataSourceConfig {


    @Bean
    public MySQLContainer mySQLContainer() {
        MySQLContainer container = new MySQLContainer<>("mysql:8.0")
            .withDatabaseName("omarze")
            .withUsername("username")
            .withPassword("password")
            .withUrlParam("createDatabaseIfNotExist", "true")
            .withUrlParam("serverTimezone", "UTC")
            .withLogConsumer(
                new Slf4jLogConsumer(
                    LoggerFactory.getLogger(getClass())
                )
            );

        container.start();

        return container;
    }


    @Bean
    @Primary
    public DataSource dataSource(MySQLContainer mySQLContainer) {
        HikariDataSource dataSource = new HikariDataSource();

        dataSource.setJdbcUrl(mySQLContainer.getJdbcUrl());
        dataSource.setUsername(mySQLContainer.getUsername());
        dataSource.setPassword(mySQLContainer.getPassword());
        dataSource.setDriverClassName(mySQLContainer.getDriverClassName());

        return dataSource;
    }


}

