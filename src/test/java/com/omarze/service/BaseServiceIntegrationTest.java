package com.omarze.service;


import com.omarze.config.TestConfig;
import com.omarze.controller.BaseControllerTest;
import com.omarze.entities.BackOfficeUser;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;

/**
 * created by julian
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
public abstract class BaseServiceIntegrationTest {


    protected final UsernamePasswordAuthenticationToken BACK_OFFICE_USER = new UsernamePasswordAuthenticationToken(
            BaseControllerTest.TEST_USER, "", Collections.singletonList(new SimpleGrantedAuthority(BackOfficeUser.ROLE_ID)));


}
