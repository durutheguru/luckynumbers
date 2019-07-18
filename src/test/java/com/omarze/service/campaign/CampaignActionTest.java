package com.omarze.service.campaign;


import com.omarze.data.CampaignDataService;
import com.omarze.dto.CampaignApprovalDTO;
import com.omarze.entities.Campaign;
import com.omarze.model.ApprovalAction;
import com.omarze.service.BaseServiceIntegrationTest;
import com.omarze.services.campaign.CampaignService;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * created by julian
 */
public abstract class CampaignActionTest extends BaseServiceIntegrationTest {


    @Autowired
    CampaignDataService campaignDataService;


    @Autowired
    CampaignService campaignService;


    Campaign campaign;


    @Before
    public void init() {
        campaign = campaignDataService.saveCampaign();
    }


    protected void approveCampaign() throws Exception {
        CampaignApprovalDTO approvalDTO = new CampaignApprovalDTO(campaign.getId(), ApprovalAction.APPROVED);
        campaign = campaignService.campaignAction(approvalDTO);
    }


}