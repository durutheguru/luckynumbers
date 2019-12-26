package com.omarze.service.campaign.handlers;


import com.omarze.api.dto.CampaignDTO;
import com.omarze.data.campaign.CampaignDataService;
import com.omarze.exception.InvalidObjectException;
import com.omarze.service.BaseServiceIntegrationTest;
import com.omarze.services.campaign.CampaignService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * created by julian
 */
public class SaveTest extends BaseServiceIntegrationTest {


    @Autowired
    private CampaignService campaignService;


    @Autowired
    private CampaignDataService campaignDataService;



    @Test(expected = InvalidObjectException.class)
    public void testAddingCampaignWithInvalidDateInterval() throws Exception {
        CampaignDTO campaignDTO = campaignDataService.newInvalidStartEndDateCampaignDTO();

        campaignService.addCampaign(campaignDTO);
    }


    @Test(expected = InvalidObjectException.class)
    public void testAddingCampaignWithInvalidEvaluationTimes() throws Exception {
        CampaignDTO campaignDTO = campaignDataService.newInvalidEvaluationTimeCampaignDTO();

        campaignService.addCampaign(campaignDTO);
    }


    @Test(expected = InvalidObjectException.class)
    public void testAddingCampaignWithInvalidWinnerCount() throws Exception {
        CampaignDTO campaignDTO = campaignDataService.newInvalidWinnerCountCampaignDTO();

        campaignService.addCampaign(campaignDTO);
    }


}
