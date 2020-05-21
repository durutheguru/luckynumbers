package com.omarze.controller;


import com.omarze.config.TestConfig;
import org.junit.runner.RunWith;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * created by julian
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@AutoConfigureMockMvc
public abstract class BaseControllerTest {

    protected Logger logger = LoggerFactory.getLogger(BaseControllerTest.class);


    public static final String TEST_USER = "Test User";


    @Value("${spring.data.rest.basePath}")
    protected String API_BASE_PATH;


    @Autowired
    protected MockMvc mockMvc;


}

