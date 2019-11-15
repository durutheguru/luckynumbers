package com.omarze.services.campaign.job;


import com.omarze.entities.CampaignStageEvaluationResult;
import com.omarze.entities.Stage;
import com.omarze.exception.ServiceException;
import com.omarze.persistence.CampaignRepository;
import com.omarze.persistence.LotteryUserCampaignRepository;
import com.omarze.services.campaign.EvaluationResultProcessor;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 *
 * The EvaluationJob runs when a group of winners is to be selected at a Stage.
 *
 *
 * created by julian
 */
@Component
public class EvaluationJob extends QuartzJobBean {


    public final static String WINNERS_COUNT = "WINNERS_COUNT";

    public final static String STAGE = "STAGE";

    public final static String CAMPAIGN_ID = "CAMPAIGN_ID";


    private final LotteryUserCampaignRepository userCampaignRepository;

    private final CampaignRepository campaignRepository;

    private final List<EvaluationResultProcessor> resultProcessors;


    public EvaluationJob(LotteryUserCampaignRepository userCampaignRepository, CampaignRepository campaignRepository, List<EvaluationResultProcessor> resultProcessors) {
        this.userCampaignRepository = userCampaignRepository;
        this.campaignRepository = campaignRepository;
        this.resultProcessors = resultProcessors;
    }


    @Override
    protected void executeInternal(JobExecutionContext jobContext) throws JobExecutionException {

        try {
            JobDataMap dataMap = jobContext.getMergedJobDataMap();

            CampaignStageEvaluationResult evaluationResult = new Evaluation()
                    .setCampaignId(dataMap.getLongValue(CAMPAIGN_ID))
                    .setStage(Stage.from(dataMap.getIntValue(STAGE)))
                    .setWinnerCount(dataMap.getIntValue(WINNERS_COUNT))
                    .setUserCampaignRepository(userCampaignRepository)
                    .setCampaignRepository(campaignRepository)
                    .run();

            EvaluationResultProcessor.process(resultProcessors, evaluationResult);
        }
        catch (ServiceException e) {
            throw new JobExecutionException(e);
        }
    }


}

