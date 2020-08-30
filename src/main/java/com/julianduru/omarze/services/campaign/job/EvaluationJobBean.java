package com.julianduru.omarze.services.campaign.job;


import com.julianduru.omarze.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;


/**
 * The EvaluationJob runs when a group of winners is to be selected at a Stage.
 * <p/>
 * <p/>
 * created by julian
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class EvaluationJobBean extends QuartzJobBean {


    private final EvaluationJobDelegate jobDelegate;


    @Override
    public void executeInternal(JobExecutionContext jobContext) throws JobExecutionException {
        try {
            log.info("Running Job: " + jobContext.getMergedJobDataMap());
            jobDelegate.runJob(jobContext.getMergedJobDataMap());
        } catch (ServiceException e) {
            log.error(e.getMessage(), e);
            throw new JobExecutionException(e);
        }
    }


}
