package com.julianduru.omarze.services.campaign;


import com.julianduru.omarze.entities.CampaignStageEvaluationResult;
import com.julianduru.omarze.entities.Email;
import com.julianduru.omarze.exception.StageResultProcessingException;
import com.julianduru.omarze.persistence.EmailRepository;
import com.julianduru.omarze.services.campaign.email.StageWinnerEmailComposer;
import com.julianduru.omarze.services.campaign.email.StageWinnerEmailRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * created by julian
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class EvaluationResultEmailHandler implements EvaluationResultProcessor {


    final StageWinnerEmailComposer stageWinnerEmailComposer;


    final EmailRepository emailRepository;


    @Override
    public void processResult(CampaignStageEvaluationResult evaluationResult) throws StageResultProcessingException {
        evaluationResult
            .getUserCampaigns()
            .forEach(r -> {
                Email email = stageWinnerEmailComposer.composeEmail(
                    StageWinnerEmailRequest.builder()
                        .stage(evaluationResult.getStage())
                        .lotteryUserCampaign(r)
                        .build()
                );
                emailRepository.save(email);
            });

        log.info("Handled Dispatch of Campaign Stage Evaluation Result {}", evaluationResult.getId());
    }


    @Override
    public Integer order() {
        return 3000;
    }


}

