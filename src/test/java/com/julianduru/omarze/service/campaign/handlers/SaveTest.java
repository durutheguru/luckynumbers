package com.julianduru.omarze.service.campaign.handlers;


import com.julianduru.omarze.api.dto.CampaignDTO;
import com.julianduru.omarze.data.campaign.CampaignDataProvider;
import com.julianduru.omarze.exception.InvalidObjectException;
import com.julianduru.omarze.service.BaseServiceIntegrationTest;
import com.julianduru.omarze.services.campaign.CampaignService;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * created by julian
 */
public class SaveTest extends BaseServiceIntegrationTest {


    @Autowired
    private CampaignService campaignService;


    @Autowired
    private CampaignDataProvider campaignDataProvider;



    @Test
    @Disabled
    public void testAddingCampaignWithInvalidDateInterval() throws Exception {
        assertThrows(InvalidObjectException.class, () -> {
            CampaignDTO campaignDTO = campaignDataProvider.newInvalidStartEndDateCampaignDTO();

//        campaignService.addCampaign(campaignDTO);
        });
    }


    @Test
    @Disabled
    public void testAddingCampaignWithInvalidEvaluationTimes() throws Exception {
        assertThrows(InvalidObjectException.class, () -> {
            CampaignDTO campaignDTO = campaignDataProvider.newInvalidEvaluationTimeCampaignDTO();

//        campaignService.addCampaign(campaignDTO);
        });
    }


    @Test
    @Disabled
    public void testAddingCampaignWithInvalidWinnerCount() throws Exception {
        assertThrows(InvalidObjectException.class, () -> {
            CampaignDTO campaignDTO = campaignDataProvider.newInvalidWinnerCountCampaignDTO();

//        campaignService.addCampaign(campaignDTO);
        });
    }


}
