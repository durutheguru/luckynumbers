package com.omarze.service.campaign.job;


import com.omarze.data.campaign.CampaignDataService;
import com.omarze.data.lotteryuser.LotteryUserCampaignDataService;
import com.omarze.entities.Campaign;
import com.omarze.entities.CampaignStageEvaluationResult;
import com.omarze.entities.CampaignStatus;
import com.omarze.entities.StageDescription;
import com.omarze.persistence.CampaignRepository;
import com.omarze.service.BaseServiceIntegrationTest;
import com.omarze.services.campaign.job.EvaluationJobDelegate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * created by julian
 */
public class EvaluationTest extends BaseServiceIntegrationTest {


    @Autowired
    private CampaignDataService campaignDataService;


    @Autowired
    private LotteryUserCampaignDataService userCampaignDataService;


    @Autowired
    private CampaignRepository campaignRepository;


    @Autowired
    EvaluationJobDelegate evaluationJobDelegate;


    private Campaign campaign;


    @Before
    public void before() {
        campaign = campaignDataService.saveActiveCampaign();
        userCampaignDataService.saveUserCampaigns(campaign, 40);
    }


    @Test
    public void testEvaluationJobSelectsStageWinners() throws Exception {
        List<StageDescription> stageDescriptions = campaign.stageDescriptions;

        Map<String, Object> jobMap = new HashMap<>();
        for (StageDescription sd : stageDescriptions) {
            jobMap.put(EvaluationJobDelegate.CAMPAIGN_ID, campaign.getId());
            jobMap.put(EvaluationJobDelegate.STAGE, sd.getStage().value);
            jobMap.put(EvaluationJobDelegate.WINNERS_COUNT, sd.getWinnersCount());

            CampaignStageEvaluationResult result = evaluationJobDelegate.runJob(jobMap);

            Assert.assertNotNull(result);
            Assert.assertEquals(sd.getWinnersCount(), result.getWinningNumbers().size());

            jobMap.clear();
        }

        Campaign finishedCampaign = campaignRepository.findById(campaign.getId()).orElseThrow(EntityNotFoundException::new);
        Assert.assertEquals(finishedCampaign.getCampaignStatus(), CampaignStatus.COMPLETED);
    }


}

