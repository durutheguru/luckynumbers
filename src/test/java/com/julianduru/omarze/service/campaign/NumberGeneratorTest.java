package com.julianduru.omarze.service.campaign;


import com.julianduru.omarze.data.lotteryuser.LotteryUserCampaignDataService;
import com.julianduru.omarze.entities.LotteryUserCampaign;
import com.julianduru.omarze.service.BaseServiceIntegrationTest;
import com.julianduru.omarze.services.campaign.NumberGenerator;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
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
    @Disabled
    public void testGeneratingUniqueUserNumber() throws Exception {
        String luckyNumber = "7590829849";
        String nextLuckyNumber = "7408247982";

        LotteryUserCampaign userCampaign = userCampaignDataService.saveUserCampaign(luckyNumber);

        NumberGenerator numberGeneratorSpy = Mockito.spy(numberGenerator);
        doReturn(luckyNumber, luckyNumber, nextLuckyNumber).when(numberGeneratorSpy).generateUserNumber();

        String numberGenerated = numberGeneratorSpy.generateUniqueUserNumber(userCampaign.getCampaign());

        verify(numberGeneratorSpy, times(3)).isExistingCampaignNumber(any(), anyString());
        assertThat(nextLuckyNumber).isEqualTo(numberGenerated);
    }


}
