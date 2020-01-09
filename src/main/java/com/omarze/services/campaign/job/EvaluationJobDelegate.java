package com.omarze.services.campaign.job;


import com.omarze.entities.CampaignStageEvaluationResult;
import com.omarze.entities.Stage;
import com.omarze.exception.ServiceException;
import com.omarze.persistence.CampaignRepository;
import com.omarze.persistence.CampaignStageEvaluationResultRepository;
import com.omarze.persistence.LotteryUserCampaignRepository;
import com.omarze.services.campaign.EvaluationResultProcessor;
import lombok.RequiredArgsConstructor;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * created by julian
 */
@Component
@RequiredArgsConstructor
public class EvaluationJobDelegate {


    public final static String WINNERS_COUNT = "WINNERS_COUNT";

    public final static String STAGE = "STAGE";

    public final static String CAMPAIGN_ID = "CAMPAIGN_ID";


    private final LotteryUserCampaignRepository userCampaignRepository;

    private final CampaignRepository campaignRepository;

    private final CampaignStageEvaluationResultRepository evaluationResultRepository;

    private final List<EvaluationResultProcessor> resultProcessors;


    @Transactional
    public CampaignStageEvaluationResult runJob(Map<String, Object> jobMap) throws ServiceException {
        return EvaluationResultProcessor.process(
            resultProcessors,

            Evaluation
                .builder()
                .campaignId((Long) jobMap.get(CAMPAIGN_ID))
                .stage(Stage.from((Integer) jobMap.get(STAGE)))
                .winnerCount((Integer) jobMap.get(WINNERS_COUNT))
                .userCampaignRepository(userCampaignRepository)
                .campaignRepository(campaignRepository)
                .evaluationResultRepository(evaluationResultRepository)
                .build()
                .execute()
        );
    }


}


