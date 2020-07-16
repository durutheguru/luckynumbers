package com.omarze.service.campaign;


import com.omarze.data.campaign.CampaignDataProvider;
import com.omarze.api.dto.CampaignActionDTO;
import com.omarze.entities.Campaign;
import com.omarze.model.ApprovalAction;
import com.omarze.services.campaign.CampaignService;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * created by julian
 */
public abstract class CampaignActionTest {


    @Autowired
    CampaignDataProvider campaignDataProvider;


    @Autowired
    CampaignService campaignService;


    Campaign campaign;


    @Before
    public void init() {
        campaign = campaignDataProvider.saveEntity();
    }


    protected void approveCampaign() throws Exception {
        CampaignActionDTO approvalDTO = new CampaignActionDTO(campaign.getId(), ApprovalAction.APPROVED);
        campaign = campaignService.campaignAction(approvalDTO);
    }


}
