package com.julianduru.omarze.controller;


import com.julianduru.omarze.config.TestConfig;
import com.julianduru.omarze.config.TestDataSourceConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;

/**
 * created by julian
 */
@Slf4j
@Testcontainers
@ExtendWith({SpringExtension.class})
@SpringBootTest(classes = {
    TestConfig.class,
    TestDataSourceConfig.class,
})
@AutoConfigureMockMvc
public abstract class BaseControllerTest {


    public static final String TEST_USER = "Test User";


    @Value("${spring.data.rest.basePath}")
    protected String API_BASE_PATH;


    @Autowired
    protected MockMvc mockMvc;



//    @DynamicPropertySource
//    static void mysqlDatabaseProperties(DynamicPropertyRegistry registry) {
//        registry.add("spring.datasource.url", container::getJdbcUrl);
//        registry.add("spring.datasource.password", container::getPassword);
//        registry.add("spring.datasource.username", container::getUsername);
//    }


}

