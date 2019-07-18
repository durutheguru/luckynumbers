package com.omarze.services.campaign;


import com.omarze.entities.Campaign;
import com.omarze.entities.LotteryUserCampaign;
import com.omarze.entities.Stage;
import com.omarze.entities.StageDescription;
import com.omarze.persistence.CampaignRepository;
import com.omarze.persistence.LotteryUserCampaignRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

/**
 * created by julian
 */
@Component
public class CampaignActivationManager {


    private final CampaignRepository campaignRepository;

    private final LotteryUserCampaignRepository userCampaignRepository;


    public CampaignActivationManager(CampaignRepository campaignRepository, LotteryUserCampaignRepository userCampaignRepository) {
        this.campaignRepository = campaignRepository;
        this.userCampaignRepository = userCampaignRepository;
    }


    public void activateCampaign(Campaign campaign) {
        Assert.notNull(campaign, "Campaign Parameter should not be null");





        LotteryUserCampaign userCampaign = new LotteryUserCampaign();

    }



}
