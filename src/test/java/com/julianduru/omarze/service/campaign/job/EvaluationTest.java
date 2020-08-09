package com.julianduru.omarze.service.campaign.job;


import com.julianduru.omarze.entities.*;
import com.julianduru.omarze.data.campaign.CampaignDataProvider;
import com.julianduru.omarze.data.lotteryuser.LotteryUserCampaignDataService;
import com.julianduru.omarze.persistence.CampaignRepository;
import com.julianduru.omarze.service.BaseServiceIntegrationTest;
import com.julianduru.omarze.services.campaign.job.EvaluationJobDelegate;
import com.julianduru.omarze.util.TestConstants;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * created by julian
 */
@WithMockUser(username = TestConstants.TEST_USER, authorities = {BackOfficeUser.ROLE_ID})
public class EvaluationTest extends BaseServiceIntegrationTest {


    @Autowired
    private CampaignDataProvider campaignDataProvider;


    @Autowired
    private LotteryUserCampaignDataService userCampaignDataService;


    @Autowired
    private CampaignRepository campaignRepository;


    @Autowired
    EvaluationJobDelegate evaluationJobDelegate;


    private Campaign campaign;


    @Before
    public void before() {
        campaign = campaignDataProvider.saveActiveCampaign();
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

