package com.omarze.services.campaign.job;


import com.omarze.entities.Campaign;
import com.omarze.entities.CampaignStageEvaluationResult;
import com.omarze.entities.Stage;
import com.omarze.exception.ServiceException;
import com.omarze.persistence.CampaignRepository;
import com.omarze.persistence.LotteryUserCampaignRepository;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;


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


    private LotteryUserCampaignRepository userCampaignRepository;

    private CampaignRepository campaignRepository;


    @Autowired
    public EvaluationJob setUserCampaignRepository(LotteryUserCampaignRepository userCampaignRepository) {
        this.userCampaignRepository = userCampaignRepository;
        return this;
    }


    @Autowired
    public EvaluationJob setCampaignRepository(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
        return this;
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

            
        }
        catch (ServiceException e) {
            throw new JobExecutionException(e);
        }
    }


}
