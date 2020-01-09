package com.omarze.service.campaign;


import com.omarze.data.campaign.CampaignDataService;
import com.omarze.entities.Campaign;
import com.omarze.entities.CampaignStatus;
import com.omarze.persistence.CampaignRepository;
import com.omarze.service.BaseServiceIntegrationTest;
import com.omarze.services.campaign.CampaignActivationScheduler;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * created by julian
 */
public class CampaignActivationSchedulerTest extends BaseServiceIntegrationTest {


    private CampaignActivationScheduler campaignActivationScheduler;


    private CampaignDataService campaignDataService;


    private CampaignRepository campaignRepository;


    @Autowired
    public CampaignActivationSchedulerTest setCampaignDataService(CampaignDataService campaignDataService) {
        this.campaignDataService = campaignDataService;
        return this;
    }


    @Autowired
    public CampaignActivationSchedulerTest setCampaignActivationScheduler(CampaignActivationScheduler campaignActivationScheduler) {
        this.campaignActivationScheduler = campaignActivationScheduler;
        return this;
    }


    @Autowired
    public CampaignActivationSchedulerTest setCampaignRepository(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
        return this;
    }


    @Test
    public void testActivatingAllDueCampaigns() throws Exception {
        List<Campaign> campaigns = saveCampaigns();
        campaignActivationScheduler.activateDueCampaigns();

        List<Campaign> savedCampaigns = campaignRepository.findAllById(campaigns.stream().map(Campaign::getId).collect(Collectors.toList()));

        Assert.assertEquals(savedCampaigns.get(0).getCampaignStatus(), CampaignStatus.ACTIVE);
        Assert.assertEquals(savedCampaigns.get(1).getCampaignStatus(), CampaignStatus.ACTIVE);
        Assert.assertEquals(savedCampaigns.get(2).getCampaignStatus(), CampaignStatus.APPROVED);
    }


    private List<Campaign> saveCampaigns() {
        List<Campaign> campaigns = Arrays.asList(
                Campaign.builder()
                        .campaignStatus(CampaignStatus.APPROVED)
                        .stageDescriptions(campaignDataService.campaignStageDescriptions())
                        .startDate(LocalDate.now().minusDays(5)).build(),

                Campaign.builder()
                        .campaignStatus(CampaignStatus.APPROVED)
                        .stageDescriptions(campaignDataService.campaignStageDescriptions())
                        .startDate(LocalDate.now()).build(),

                Campaign.builder()
                        .campaignStatus(CampaignStatus.APPROVED)
                        .stageDescriptions(campaignDataService.campaignStageDescriptions())
                        .startDate(LocalDate.now().plusDays(5)).build()
        );

        return campaignDataService.saveCampaigns(campaigns);
    }


}
