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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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


    @BeforeEach
    public void before() {
        campaignList.add(campaignDataProvider.save());
    }


    @Test
    @Disabled
    public void testApprovingCampaign() throws Exception {
        Auth.setContext(BACK_OFFICE_USER);

        CampaignActionDTO action = new CampaignActionDTO(campaignList.get(0).getId(), ApprovalAction.APPROVED);

        Campaign campaign = campaignService.campaignAction(action);

        assertThat(campaign.getCampaignStatus()).isEqualTo(CampaignStatus.APPROVED);
    }


    @Test
    @Disabled
    public void testDisapprovingCampaign() throws Exception {
        Auth.setContext(BACK_OFFICE_USER);

        CampaignActionDTO action = new CampaignActionDTO(campaignList.get(0).getId(), ApprovalAction.DISAPPROVED);

        Campaign campaign = Approval.builder()
                .campaignAction(action)
                .campaignRepository(campaignRepository)
                .build()
                .execute();

        assertThat(campaign.getCampaignStatus()).isEqualTo(CampaignStatus.DISAPPROVED);
    }


}

