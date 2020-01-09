package com.omarze.services.campaign.job;


import com.omarze.exception.ServiceException;
import com.omarze.util.AppLogger;
import lombok.RequiredArgsConstructor;
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
@Component
@RequiredArgsConstructor
public class EvaluationJobBean extends QuartzJobBean {


    private final EvaluationJobDelegate jobDelegate;


    @Override

    public void executeInternal(JobExecutionContext jobContext) throws JobExecutionException {
        try {
            AppLogger.info("Running Job: " + jobContext.getMergedJobDataMap());
            jobDelegate.runJob(jobContext.getMergedJobDataMap());
        } catch (ServiceException e) {
            throw new JobExecutionException(e);
        }
    }


}
