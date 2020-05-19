package com.omarze.service.lotteryuser;


import com.omarze.data.lotteryuser.LotteryUserDataProvider;
import com.omarze.entities.LotteryUser;
import com.omarze.service.BaseServiceIntegrationTest;
import com.omarze.services.lotteryuser.LotteryUserService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * created by julian
 */
public class SaveTest extends BaseServiceIntegrationTest {


    @Autowired
    private LotteryUserService lotteryUserService;


    @Autowired
    private LotteryUserDataProvider lotteryUserDataProvider;


    @Test
    public void testAddingNewLotteryUser() throws Exception {
        LotteryUser lotteryUser = lotteryUserService.addLotteryUser(lotteryUserDataProvider.newLotteryUserDTO());

        Assert.assertNotNull(lotteryUser);
    }


}
