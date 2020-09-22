package com.julianduru.omarze.service;


import com.julianduru.omarze.config.TestConfig;
import com.julianduru.omarze.controller.BaseControllerTest;
import com.julianduru.omarze.entities.BackOfficeUser;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

/**
 * created by julian
 */
@ExtendWith({SpringExtension.class})
@SpringBootTest(classes = {TestConfig.class})
public abstract class BaseServiceIntegrationTest {


    protected final UsernamePasswordAuthenticationToken BACK_OFFICE_USER = new UsernamePasswordAuthenticationToken(
            BaseControllerTest.TEST_USER, "", Collections.singletonList(new SimpleGrantedAuthority(BackOfficeUser.ROLE_ID)));


}
