package com.omarze.service.campaign;


import com.omarze.data.CampaignDataService;
import com.omarze.entities.Campaign;
import com.omarze.entities.CampaignStatus;
import com.omarze.persistence.CampaignRepository;
import com.omarze.service.BaseServiceIntegrationTest;
import com.omarze.services.campaign.CampaignActivationScheduler;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * created by julian
 */
public class CampaignActivationManagerTest extends BaseServiceIntegrationTest {


    private CampaignActivationScheduler campaignActivationScheduler;


    private CampaignDataService campaignDataService;


    private CampaignRepository campaignRepository;


    @Autowired
    public CampaignActivationManagerTest setCampaignDataService(CampaignDataService campaignDataService) {
        this.campaignDataService = campaignDataService;
        return this;
    }


    @Autowired
    public CampaignActivationManagerTest setCampaignActivationScheduler(CampaignActivationScheduler campaignActivationScheduler) {
        this.campaignActivationScheduler = campaignActivationScheduler;
        return this;
    }


    @Autowired
    public CampaignActivationManagerTest setCampaignRepository(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
        return this;
    }


    @Test
    public void testActivatingAllDueCampaigns() throws Exception {
        saveCampaigns();
        campaignActivationScheduler.activateDueCampaigns();

        List<Campaign> savedCampaigns = campaignRepository.findAll();

        int activeCount = 0;
        int inactiveCount = 0;

        for (Campaign campaign : savedCampaigns) {
            switch (campaign.getCampaignStatus()) {
                case ACTIVE:
                    ++activeCount;
                    break;
                default:
                    ++inactiveCount;
            }
        }

        Assert.assertEquals(activeCount, 2);
        Assert.assertEquals(inactiveCount, 1);
    }


    private List<Campaign> saveCampaigns() {
        List<Campaign> campaigns = Arrays.asList(
                new Campaign()
                        .setCampaignStatus(CampaignStatus.APPROVED)
                        .setStartDate(LocalDate.now().minusDays(5)),

                new Campaign()
                        .setCampaignStatus(CampaignStatus.APPROVED)
                        .setStartDate(LocalDate.now()),

                new Campaign()
                        .setCampaignStatus(CampaignStatus.APPROVED)
                        .setStartDate(LocalDate.now().plusDays(5))
        );

        return campaignDataService.saveCampaigns(campaigns);
    }


}
