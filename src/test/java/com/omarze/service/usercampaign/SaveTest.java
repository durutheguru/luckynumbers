package com.omarze.service.usercampaign;


import com.omarze.api.dto.LotteryUserCampaignDTO;
import com.omarze.data.lotteryuser.LotteryUserCampaignDataService;
import com.omarze.entities.BackOfficeUser;
import com.omarze.entities.LotteryUserCampaign;
import com.omarze.exception.DuplicateUserCampaignException;
import com.omarze.service.BaseServiceIntegrationTest;
import com.omarze.services.usercampaign.LotteryUserCampaignService;
import com.omarze.util.TestConstants;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;

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
    @Ignore
    public void testAddingUserCampaign() throws Exception {
        LotteryUserCampaignDTO userCampaignDTO = userCampaignDataService.newUserCampaignDTO();

        LotteryUserCampaign userCampaign = userCampaignService.addUserCampaign(userCampaignDTO);

        Assert.assertNotNull(userCampaign);
    }


    @Test(expected = DuplicateUserCampaignException.class)
    @Ignore
    public void testUserSubscribingToSameCampaignMoreThanOnce() throws Exception {
        LotteryUserCampaignDTO userCampaignDTO = userCampaignDataService.newUserCampaignDTO();

        userCampaignService.addUserCampaign(userCampaignDTO);
        userCampaignService.addUserCampaign(userCampaignDTO);
    }


}

