package com.julianduru.omarze.service.usercampaign;


import com.julianduru.omarze.api.dto.LotteryUserCampaignDTO;
import com.julianduru.omarze.data.lotteryuser.LotteryUserCampaignDataService;
import com.julianduru.omarze.entities.BackOfficeUser;
import com.julianduru.omarze.entities.LotteryUserCampaign;
import com.julianduru.omarze.exception.DuplicateUserCampaignException;
import com.julianduru.omarze.service.BaseServiceIntegrationTest;
import com.julianduru.omarze.services.usercampaign.LotteryUserCampaignService;
import com.julianduru.omarze.util.TestConstants;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * created by julian
 */
@WithMockUser(username = TestConstants.TEST_USER, authorities = {BackOfficeUser.ROLE_ID})
public class SaveTest extends BaseServiceIntegrationTest {


    @Autowired
    private LotteryUserCampaignDataService userCampaignDataService;


    @Autowired
    private LotteryUserCampaignService userCampaignService;



    @Test
    @Disabled
    public void testAddingUserCampaign() throws Exception {
        LotteryUserCampaignDTO userCampaignDTO = userCampaignDataService.newUserCampaignDTO();

        LotteryUserCampaign userCampaign = userCampaignService.addUserCampaign(userCampaignDTO);

        assertThat(userCampaign).isNotNull();
    }


    @Test
    @Disabled
    public void testUserSubscribingToSameCampaignMoreThanOnce() throws Exception {
        assertThrows(DuplicateUserCampaignException.class, () -> {
            LotteryUserCampaignDTO userCampaignDTO = userCampaignDataService.newUserCampaignDTO();

            userCampaignService.addUserCampaign(userCampaignDTO);
            userCampaignService.addUserCampaign(userCampaignDTO);
        });
    }


}

