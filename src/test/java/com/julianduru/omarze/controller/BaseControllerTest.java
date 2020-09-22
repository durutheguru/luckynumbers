package com.julianduru.omarze.controller;


import com.julianduru.omarze.config.TestConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * created by julian
 */
@Slf4j
@ExtendWith({SpringExtension.class})
@SpringBootTest(classes = {
    TestConfig.class,
})
@AutoConfigureMockMvc
public abstract class BaseControllerTest {


    public static final String TEST_USER = "Test User";


    @Value("${spring.data.rest.basePath}")
    protected String API_BASE_PATH;


    @Autowired
    protected MockMvc mockMvc;


}

