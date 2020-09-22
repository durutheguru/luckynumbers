package com.julianduru.omarze.service.lotteryuser;


import com.julianduru.omarze.data.lotteryuser.LotteryUserDataProvider;
import com.julianduru.omarze.entities.LotteryUser;
import com.julianduru.omarze.service.BaseServiceIntegrationTest;
import com.julianduru.omarze.services.lotteryuser.LotteryUserService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

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
        assertThat(lotteryUser).isNotNull();
    }


}
