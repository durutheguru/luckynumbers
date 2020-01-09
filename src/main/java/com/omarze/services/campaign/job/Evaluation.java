package com.omarze.services.campaign.job;


import com.omarze.entities.*;
import com.omarze.exception.EntityNotFoundException;
import com.omarze.exception.ServiceException;
import com.omarze.persistence.CampaignRepository;
import com.omarze.persistence.CampaignStageEvaluationResultRepository;
import com.omarze.persistence.LotteryUserCampaignRepository;
import com.omarze.services.CommandBase;
import lombok.Builder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * created by julian
 */
public class Evaluation extends CommandBase<CampaignStageEvaluationResult> {


    @NotNull(message = "Winner count cannot be empty")
    private Integer winnerCount;


    @NotNull(message = "Stage cannot be empty")
    private Stage stage;


    @NotNull
    private Long campaignId;


    @NotNull
    private LotteryUserCampaignRepository userCampaignRepository;


    @NotNull
    private CampaignRepository campaignRepository;


    @NotNull
    private CampaignStageEvaluationResultRepository evaluationResultRepository;


    private Campaign campaign;


    private Random random = new Random(System.currentTimeMillis());


    private List<Integer> luckyIndices = new ArrayList<>();


    private List<LotteryUserCampaign> luckyUserCampaigns = new ArrayList<>();


    @Builder
    private Evaluation(
        Integer winnerCount,
        Stage stage,
        Long campaignId,
        LotteryUserCampaignRepository userCampaignRepository,
        CampaignRepository campaignRepository,
        CampaignStageEvaluationResultRepository evaluationResultRepository
    ) {
        this.winnerCount = winnerCount;
        this.stage = stage;
        this.campaignId = campaignId;
        this.userCampaignRepository = userCampaignRepository;
        this.campaignRepository = campaignRepository;
        this.evaluationResultRepository = evaluationResultRepository;
    }


    @Override
    protected CampaignStageEvaluationResult execute_() throws ServiceException {
        userCampaignRepository.updateParticipatingUserCampaignsToStatus(campaignId, LotteryUserCampaignStatus.EVALUATING);

        loadCampaign();

        generateLuckyIndices();
        loadWinningUserCampaigns();
        if (campaign.isFinalStage(stage)) {
            campaign.setCampaignStatus(CampaignStatus.COMPLETED);
        }

        return result();
    }


    private void generateLuckyIndices() throws ServiceException {
        Long waitingCount = userCampaignRepository.countByCampaignStatusAndCampaign_Id(
                LotteryUserCampaignStatus.EVALUATING, campaignId
        );

        if (waitingCount < 1) {
            throw new ServiceException("Unable to proceed with Stage Evaluation. No users waiting for Lottery :(");
        }

        int luckyIndex;

        while (luckyIndices.size() < winnerCount) {
            luckyIndex = random.nextInt(waitingCount.intValue());

            if (luckyIndices.contains(luckyIndex)) {
                continue;
            }

            luckyIndices.add(luckyIndex);
            //TODO: TRIGGER Event to notify of selected winner
        }
    }


    private void loadWinningUserCampaigns() {
        int index = 0;

        int page = 0;
        int pageSize = 500;

        Page<LotteryUserCampaign> userCampaignPage;

        while (!(userCampaignPage = userCampaignRepository.findByCampaignStatusAndCampaign_Id(
                LotteryUserCampaignStatus.EVALUATING,
                campaignId,
                PageRequest.of(page++, pageSize))).isEmpty()) {

            for (LotteryUserCampaign campaign : userCampaignPage) {
                if (luckyIndices.contains(index)) {
                    campaign.setCampaignStatus(LotteryUserCampaignStatus.CAMPAIGN_WINNER);
                    luckyUserCampaigns.add(campaign);
                }
                else {
                    campaign.setCampaignStatus(LotteryUserCampaignStatus.LOSER);
                }

                ++index;
            }

        }
    }


    private void loadCampaign() throws EntityNotFoundException {
        campaign = campaignRepository
                .findById(campaignId)
                .orElseThrow(() -> new EntityNotFoundException("Campaign", campaignId));
    }


    private CampaignStageEvaluationResult result() throws EntityNotFoundException {
        CampaignStageEvaluationResult result = new CampaignStageEvaluationResult();

        result.setStage(stage);
        result.setCampaign(campaign);
        result.setUserCampaigns(luckyUserCampaigns);
        result.setWinningNumbers(
            luckyUserCampaigns
                .stream()
                .map(LotteryUserCampaign::getUserNumber)
                .collect(Collectors.toList())
        );

        return evaluationResultRepository.save(result);
    }


}
