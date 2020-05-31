package com.omarze.service.campaign.handlers;


import com.omarze.api.dto.CampaignActionDTO;
import com.omarze.controller.BaseControllerTest;
import com.omarze.data.campaign.CampaignDataService;
import com.omarze.entities.BackOfficeUser;
import com.omarze.entities.Campaign;
import com.omarze.entities.CampaignStatus;
import com.omarze.model.ApprovalAction;
import com.omarze.persistence.CampaignRepository;
import com.omarze.security.Auth;
import com.omarze.service.BaseServiceIntegrationTest;
import com.omarze.services.campaign.CampaignService;
import com.omarze.services.campaign.handlers.Approval;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.ArrayList;
import java.util.List;

/**
 * created by julian
 */
public class ApprovalTest extends BaseServiceIntegrationTest {


    @Autowired
    private CampaignRepository campaignRepository;


    @Autowired
    private CampaignDataService campaignDataService;


    @Autowired
    private CampaignService campaignService;


    private List<Campaign> campaignList = new ArrayList<>();


    @Before
    public void before() {
        campaignList.add(campaignDataService.saveCampaign());
    }


    @Test
    @Ignore
    public void testApprovingCampaign() throws Exception {
        Auth.setContext(BACK_OFFICE_USER);

        CampaignActionDTO action = new CampaignActionDTO(campaignList.get(0).getId(), ApprovalAction.APPROVED);

        Campaign campaign = campaignService.campaignAction(action);

        Assert.assertEquals(CampaignStatus.APPROVED, campaign.getCampaignStatus());
    }


    @Test
    @Ignore
    public void testDisapprovingCampaign() throws Exception {
        Auth.setContext(BACK_OFFICE_USER);

        CampaignActionDTO action = new CampaignActionDTO(campaignList.get(0).getId(), ApprovalAction.DISAPPROVED);

        Campaign campaign = Approval.builder()
                .campaignAction(action)
                .campaignRepository(campaignRepository)
                .build()
                .execute();

        Assert.assertEquals(CampaignStatus.DISAPPROVED, campaign.getCampaignStatus());
    }


}

