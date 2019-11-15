package com.omarze.services.campaign;


import com.omarze.entities.CampaignStageEvaluationResult;
import com.omarze.exception.StageResultProcessingException;
import org.springframework.stereotype.Component;

/**
 * created by julian
 */
@Component
public class EvaluationResultEmailHandler implements EvaluationResultProcessor {


    @Override
    public void processResult(CampaignStageEvaluationResult evaluationResult) throws StageResultProcessingException {

    }


    @Override
    public Integer order() {
        return 3000;
    }


}
