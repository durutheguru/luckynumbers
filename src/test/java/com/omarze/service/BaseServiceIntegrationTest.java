package com.omarze.service;


import com.omarze.config.TestConfig;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * created by julian
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
public abstract class BaseServiceIntegrationTest {



}
