package com.omarze.exception;


import com.omarze.entities.CampaignStageEvaluationResult;

/**
 * created by julian
 */
public class StageResultProcessingException extends ServiceException {


    public final static Integer CODE = 1400929;


    public StageResultProcessingException(CampaignStageEvaluationResult evaluationResult) {
        super("Error occurred while processing Evaluation Result");
    }


    public StageResultProcessingException(CampaignStageEvaluationResult evaluationResult, String message) {
        super("Error occurred while processing Evaluation Result. " + message);
    }


    @Override
    public Integer generateCode() {
        return CODE;
    }


}
