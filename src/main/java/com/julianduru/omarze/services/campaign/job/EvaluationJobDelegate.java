package com.julianduru.omarze.services.campaign.job;


import com.julianduru.omarze.entities.CampaignStageEvaluationResult;
import com.julianduru.omarze.entities.Stage;
import com.julianduru.omarze.exception.ServiceException;
import com.julianduru.omarze.persistence.CampaignRepository;
import com.julianduru.omarze.persistence.CampaignStageEvaluationResultRepository;
import com.julianduru.omarze.persistence.LotteryUserCampaignRepository;
import com.julianduru.omarze.services.campaign.EvaluationResultProcessor;
import lombok.RequiredArgsConstructor;
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


    public static final String WINNERS_COUNT = "WINNERS_COUNT";

    public static final String STAGE = "STAGE";

    public static final String CAMPAIGN_ID = "CAMPAIGN_ID";


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


