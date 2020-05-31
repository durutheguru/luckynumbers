package com.omarze.service.backoffice;


import com.omarze.entities.BackOfficeUser;
import com.omarze.service.BaseServiceIntegrationTest;
import com.omarze.services.backoffice.BackOfficeUserService;
import com.omarze.util.TestConstants;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;

/**
 * created by julian
 */
@WithMockUser(username = TestConstants.TEST_USER, authorities = {BackOfficeUser.ROLE_ID})
public class BackOfficeInitializationServiceTest extends BaseServiceIntegrationTest {


    @Autowired
    private BackOfficeUserService backOfficeUserService;



    @Test
    public void testBackOfficeUserIsAlwaysCreatedOnStartup() {
        Assert.assertTrue(backOfficeUserService.backOfficeUserExists());
    }


}
