package com.omarze.services.campaign;


import com.omarze.entities.CampaignStageEvaluationResult;
import com.omarze.exception.StageResultProcessingException;
import com.omarze.util.AppLogger;
import org.springframework.stereotype.Component;

/**
 * created by julian
 */
@Component
public class EvaluationResultEmailHandler implements EvaluationResultProcessor {


    @Override
    public void processResult(CampaignStageEvaluationResult evaluationResult) throws StageResultProcessingException {
        AppLogger.info("Will treat Campaign Stage Evaluation Result");
    }


    @Override
    public Integer order() {
        return 3000;
    }


}

