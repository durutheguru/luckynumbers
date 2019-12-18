package com.omarze.service.backoffice;


import com.omarze.service.BaseServiceIntegrationTest;
import com.omarze.services.backoffice.BackOfficeUserService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * created by julian
 */
public class BackOfficeInitializationServiceTest extends BaseServiceIntegrationTest {


    @Autowired
    private BackOfficeUserService backOfficeUserService;



    @Test
    public void testBackOfficeUserIsAlwaysCreatedOnStartup() {
        Assert.assertTrue(backOfficeUserService.backOfficeUserExists());
    }


}
