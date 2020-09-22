package com.julianduru.omarze.service.campaign;


import com.julianduru.omarze.data.campaign.CampaignDataProvider;
import com.julianduru.omarze.api.dto.CampaignActionDTO;
import com.julianduru.omarze.entities.Campaign;
import com.julianduru.omarze.model.ApprovalAction;
import com.julianduru.omarze.services.campaign.CampaignService;

import org.junit.jupiter.api.BeforeEach;
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


    @BeforeEach
    public void init() {
        campaign = campaignDataProvider.save();
    }


    protected void approveCampaign() throws Exception {
        CampaignActionDTO approvalDTO = new CampaignActionDTO(campaign.getId(), ApprovalAction.APPROVED);
        campaign = campaignService.campaignAction(approvalDTO);
    }


}
