package com.julianduru.omarze.service.campaign;


import com.julianduru.omarze.data.campaign.CampaignDataProvider;
import com.julianduru.omarze.entities.Campaign;
import com.julianduru.omarze.entities.CampaignStatus;
import com.julianduru.omarze.persistence.CampaignRepository;
import com.julianduru.omarze.service.BaseServiceIntegrationTest;
import com.julianduru.omarze.services.campaign.CampaignActivationScheduler;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * created by julian
 */
public class CampaignActivationSchedulerTest extends BaseServiceIntegrationTest {


    private CampaignActivationScheduler campaignActivationScheduler;


    private CampaignDataProvider campaignDataProvider;


    private CampaignRepository campaignRepository;


    @Autowired
    public CampaignActivationSchedulerTest setCampaignDataProvider(CampaignDataProvider campaignDataProvider) {
        this.campaignDataProvider = campaignDataProvider;
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
    @Disabled
    public void testActivatingAllDueCampaigns() throws Exception {
        List<Campaign> campaigns = saveCampaigns();
        campaignActivationScheduler.activateDueCampaigns();

        List<Campaign> savedCampaigns = campaignRepository.findAllById(campaigns.stream().map(Campaign::getId).collect(Collectors.toList()));

        assertThat(savedCampaigns.get(0).getCampaignStatus()).isEqualTo(CampaignStatus.ACTIVE);
        assertThat(savedCampaigns.get(1).getCampaignStatus()).isEqualTo(CampaignStatus.ACTIVE);
        assertThat(savedCampaigns.get(2).getCampaignStatus()).isEqualTo(CampaignStatus.APPROVED);
    }


    private List<Campaign> saveCampaigns() {
        List<Campaign> campaigns = Arrays.asList(
                getApprovedCampaign(LocalDate.now().minusDays(5)),

                getApprovedCampaign(LocalDate.now()),

                getApprovedCampaign(LocalDate.now().plusDays(5))
        );

        return campaignDataProvider.saveCampaigns(campaigns);
    }


    private Campaign getApprovedCampaign(LocalDate startDate) {
        Campaign campaign = new Campaign();

        campaign.setCampaignStatus(CampaignStatus.APPROVED);
        campaign.setStartDate(startDate);
        campaign.setStageDescriptions(campaignDataProvider.campaignStageDescriptions());

        return campaign;
    }


}
