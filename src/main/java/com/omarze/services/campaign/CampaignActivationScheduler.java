package com.omarze.services.campaign;


import com.omarze.entities.Campaign;
import com.omarze.entities.StageDescription;
import com.omarze.exception.ServiceException;
import com.omarze.persistence.CampaignRepository;
import com.omarze.services.campaign.job.EvaluationJobBean;
import com.omarze.services.campaign.job.EvaluationJobDelegate;
import com.omarze.util.ItemBuffer;
import com.omarze.util.TimeUtil;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * created by julian
 */
@Component
@Transactional
public class CampaignActivationScheduler {

    private Logger logger = LoggerFactory.getLogger(CampaignActivationScheduler.class);

    private final Scheduler scheduler;

    private final CampaignRepository campaignRepository;

    private ItemBuffer<Campaign> activationBuffer;



    public CampaignActivationScheduler(Scheduler scheduler, CampaignRepository campaignRepository) {
        this.scheduler = scheduler;
        this.campaignRepository = campaignRepository;

        activationBuffer = new ItemBuffer<>(5, campaigns ->
            campaignRepository.activateCampaigns(
                campaigns.stream().map(Campaign::getId).collect(Collectors.toList()))
        );
    }


    @Scheduled(fixedDelay = 86_400_000l)
    public void activateDueCampaigns() throws ServiceException {
        logger.info("Activating Campaigns................");

        int page = 0;
        int size = 999;
        Page<Campaign> campaigns;

        while (!(campaigns = campaignRepository
                .findCampaignsDueForActivation(PageRequest.of(page++, size))).isEmpty()) {

            campaigns.stream().forEach(this::initializeCampaignEvaluationJobs);
        }

        activationBuffer.flush();
    }


    private void initializeCampaignEvaluationJobs(Campaign campaign) {
        try {
            List<StageDescription> stageDescriptions = campaign.getStageDescriptions();

            Trigger trigger;
            JobDetail jobDetail;

            for (StageDescription stageDescription : stageDescriptions) {
                jobDetail = createStageEvaluationJobDetail(stageDescription);
                trigger = createStageEvaluationTrigger(jobDetail, stageDescription);

                scheduler.scheduleJob(jobDetail, trigger);
            }

            activationBuffer.add(campaign);
        }
        catch (SchedulerException e) {
            logger.error(e.getMessage(), e);
        }
    }


    private JobDetail createStageEvaluationJobDetail(StageDescription stageDescription) {
        JobDataMap dataMap = new JobDataMap();
        dataMap.put(EvaluationJobDelegate.CAMPAIGN_ID, stageDescription.getCampaign().getId());
        dataMap.put(EvaluationJobDelegate.STAGE, stageDescription.getStage().value);
        dataMap.put(EvaluationJobDelegate.WINNERS_COUNT, stageDescription.getWinnersCount());

        return JobBuilder.newJob(EvaluationJobBean.class)
                .withIdentity(UUID.randomUUID().toString(), "campaign-evaluation-job-" + stageDescription.getId())
                .withDescription(String.format("Stage Evaluation Job %s %d", stageDescription.getStage(), stageDescription.getId()))
                .usingJobData(dataMap)
                .storeDurably()
                .build();
    }


    private Trigger createStageEvaluationTrigger(JobDetail jobDetail, StageDescription stageDescription) {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(jobDetail.getKey().getName(), "campaign-evaluation-job-trigger-" + stageDescription.getId())
                .withDescription("Stage Evaluation Job")
                .startAt(TimeUtil.ldtToDate(stageDescription.getEvaluationTime()))
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withMisfireHandlingInstructionFireNow())
                .build();
    }


}


