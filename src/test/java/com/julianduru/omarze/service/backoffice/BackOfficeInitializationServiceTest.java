package com.julianduru.omarze.service.backoffice;


import com.julianduru.omarze.entities.BackOfficeUser;
import com.julianduru.omarze.service.BaseServiceIntegrationTest;
import com.julianduru.omarze.services.backoffice.BackOfficeUserService;
import com.julianduru.omarze.util.TestConstants;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * created by julian
 */
@WithMockUser(username = TestConstants.TEST_USER, authorities = {BackOfficeUser.ROLE_ID})
public class BackOfficeInitializationServiceTest extends BaseServiceIntegrationTest {


    @Autowired
    private BackOfficeUserService backOfficeUserService;


    @Test
    public void testBackOfficeUserIsAlwaysCreatedOnStartup() {
        assertThat(backOfficeUserService.backOfficeUserExists()).isTrue();
    }


}
