package com.julianduru.omarze.service.campaign.handlers;


import com.julianduru.security.Auth;
import com.julianduru.omarze.api.dto.CampaignActionDTO;
import com.julianduru.omarze.data.campaign.CampaignDataProvider;
import com.julianduru.omarze.entities.Campaign;
import com.julianduru.omarze.entities.CampaignStatus;
import com.julianduru.omarze.model.ApprovalAction;
import com.julianduru.omarze.persistence.CampaignRepository;
import com.julianduru.omarze.service.BaseServiceIntegrationTest;
import com.julianduru.omarze.services.campaign.CampaignService;
import com.julianduru.omarze.services.campaign.handlers.Approval;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * created by julian
 */
public class ApprovalTest extends BaseServiceIntegrationTest {


    @Autowired
    private CampaignRepository campaignRepository;


    @Autowired
    private CampaignDataProvider campaignDataProvider;


    @Autowired
    private CampaignService campaignService;


    private List<Campaign> campaignList = new ArrayList<>();


    @Before
    public void before() {
        campaignList.add(campaignDataProvider.saveEntity());
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

