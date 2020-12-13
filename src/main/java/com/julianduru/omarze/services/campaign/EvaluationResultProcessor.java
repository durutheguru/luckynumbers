package com.julianduru.omarze.services.campaign;


import com.julianduru.omarze.entities.CampaignStageEvaluationResult;
import com.julianduru.omarze.exception.StageResultProcessingException;

import java.util.Comparator;
import java.util.List;

/**
 * created by julian
 */
public interface EvaluationResultProcessor {


    void processResult(CampaignStageEvaluationResult evaluationResult) throws StageResultProcessingException;


    Integer order();


    static CampaignStageEvaluationResult process(
        List<EvaluationResultProcessor> processorList, CampaignStageEvaluationResult result
    ) throws StageResultProcessingException {
        processorList.sort(Comparator.comparing(EvaluationResultProcessor::order));

        for (EvaluationResultProcessor processor : processorList) {
            processor.processResult(result);
        }

        return result;
    }


}
