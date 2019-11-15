package com.omarze.services.campaign;


import com.omarze.entities.CampaignStageEvaluationResult;
import com.omarze.exception.StageResultProcessingException;

import java.util.List;

/**
 * created by julian
 */
public interface EvaluationResultProcessor {


    void processResult(CampaignStageEvaluationResult evaluationResult) throws StageResultProcessingException;


    Integer order();


    static void process(List<EvaluationResultProcessor> processorList, CampaignStageEvaluationResult result) throws StageResultProcessingException {
        processorList.sort((p1, p2) -> p1.order().compareTo(p2.order()));

        for (EvaluationResultProcessor processor : processorList) {
            processor.processResult(result);
        }
    }


}
