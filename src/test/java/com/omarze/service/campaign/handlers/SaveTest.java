package com.omarze.service.campaign.handlers;


import com.omarze.api.dto.CampaignDTO;
import com.omarze.data.campaign.CampaignDataProvider;
import com.omarze.exception.InvalidObjectException;
import com.omarze.service.BaseServiceIntegrationTest;
import com.omarze.services.campaign.CampaignService;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * created by julian
 */
public class SaveTest extends BaseServiceIntegrationTest {


    @Autowired
    private CampaignService campaignService;


    @Autowired
    private CampaignDataProvider campaignDataProvider;



    @Test(expected = InvalidObjectException.class)
    @Ignore
    public void testAddingCampaignWithInvalidDateInterval() throws Exception {
        CampaignDTO campaignDTO = campaignDataProvider.newInvalidStartEndDateCampaignDTO();

//        campaignService.addCampaign(campaignDTO);
    }


    @Test(expected = InvalidObjectException.class)
    @Ignore
    public void testAddingCampaignWithInvalidEvaluationTimes() throws Exception {
        CampaignDTO campaignDTO = campaignDataProvider.newInvalidEvaluationTimeCampaignDTO();

//        campaignService.addCampaign(campaignDTO);
    }


    @Test(expected = InvalidObjectException.class)
    @Ignore
    public void testAddingCampaignWithInvalidWinnerCount() throws Exception {
        CampaignDTO campaignDTO = campaignDataProvider.newInvalidWinnerCountCampaignDTO();

//        campaignService.addCampaign(campaignDTO);
    }


}
