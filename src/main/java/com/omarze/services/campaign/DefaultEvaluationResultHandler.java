package com.omarze.services.campaign;


import com.omarze.entities.CampaignStageEvaluationResult;
import com.omarze.entities.LotteryUserCampaignStatus;
import com.omarze.exception.StageResultProcessingException;
import com.omarze.persistence.CampaignStageEvaluationResultRepository;
import com.omarze.persistence.LotteryUserCampaignRepository;
import org.springframework.stereotype.Component;


/**
 * created by julian
 */
@Component
public class DefaultEvaluationResultHandler implements EvaluationResultProcessor {

    private LotteryUserCampaignRepository userCampaignRepository;

    private CampaignStageEvaluationResultRepository evaluationResultRepository;


    public DefaultEvaluationResultHandler(LotteryUserCampaignRepository userCampaignRepository, CampaignStageEvaluationResultRepository evaluationResultRepository) {
        this.userCampaignRepository = userCampaignRepository;
        this.evaluationResultRepository = evaluationResultRepository;
    }


    @Override
    public void processResult(final CampaignStageEvaluationResult result) throws StageResultProcessingException {
        LotteryUserCampaignStatus winnerStatus = getWinnerStatus(result);
        updateUserCampaigns(result, winnerStatus);

        evaluationResultRepository.save(result);
    }


    private LotteryUserCampaignStatus getWinnerStatus(CampaignStageEvaluationResult result) throws StageResultProcessingException {
        LotteryUserCampaignStatus winnerStatus;

        if (result.isFinalStage()) {
            winnerStatus = LotteryUserCampaignStatus.CAMPAIGN_WINNER;
        }
        else {
            winnerStatus = LotteryUserCampaignStatus.WAITING;
        }

        return winnerStatus;
    }


    private void updateUserCampaigns(CampaignStageEvaluationResult result, LotteryUserCampaignStatus winnerStatus) {
        Long campaignId = result.getCampaign().getId();

        userCampaignRepository.updateUserCampaignsToStatus(campaignId, result.getWinningNumbers(), winnerStatus);
        userCampaignRepository.updateUserCampaignLosers(campaignId);
    }


    @Override
    public Integer order() {
        return 1000;
    }


}
