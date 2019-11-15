package com.omarze.controller;


import com.omarze.config.TestConfig;
import org.junit.runner.RunWith;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * created by julian
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@AutoConfigureMockMvc
public abstract class BaseControllerTest {

    protected Logger logger = LoggerFactory.getLogger(BaseControllerTest.class);

    @Autowired
    protected MockMvc mockMvc;


}
