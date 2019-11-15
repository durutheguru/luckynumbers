package com.omarze.service.campaign;


import com.omarze.data.lotteryuser.LotteryUserCampaignDataService;
import com.omarze.entities.LotteryUserCampaign;
import com.omarze.service.BaseServiceIntegrationTest;
import com.omarze.services.campaign.NumberGenerator;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Mockito.*;

/**
 * created by julian
 */
public class NumberGeneratorTest extends BaseServiceIntegrationTest {


    @Autowired
    private NumberGenerator numberGenerator;

    @Autowired
    private LotteryUserCampaignDataService userCampaignDataService;



    @Test
    public void testGeneratingUniqueUserNumber() throws Exception {
        String luckyNumber = "7590829849";
        String nextLuckyNumber = "7408247982";

        LotteryUserCampaign userCampaign = userCampaignDataService.saveUserCampaign(luckyNumber);

        NumberGenerator numberGeneratorSpy = Mockito.spy(numberGenerator);
        doReturn(luckyNumber, luckyNumber, nextLuckyNumber).when(numberGeneratorSpy).generateUserNumber();

        String numberGenerated = numberGeneratorSpy.generateUniqueUserNumber(userCampaign.getCampaign());

        verify(numberGeneratorSpy, times(3)).isExistingCampaignNumber(any(), anyString());
        Assert.assertEquals(nextLuckyNumber, numberGenerated);
    }


}
