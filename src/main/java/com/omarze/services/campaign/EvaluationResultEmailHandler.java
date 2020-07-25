package com.omarze.services.campaign;


import com.omarze.entities.CampaignStageEvaluationResult;
import com.omarze.exception.StageResultProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * created by julian
 */
@Slf4j
@Component
public class EvaluationResultEmailHandler implements EvaluationResultProcessor {


    @Override
    public void processResult(CampaignStageEvaluationResult evaluationResult) throws StageResultProcessingException {
        log.info("Will treat Campaign Stage Evaluation Result");
    }


    @Override
    public Integer order() {
        return 3000;
    }


}

