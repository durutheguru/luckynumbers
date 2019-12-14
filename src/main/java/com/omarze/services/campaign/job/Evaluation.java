package com.omarze.services.campaign.job;


import com.omarze.entities.CampaignStageEvaluationResult;
import com.omarze.entities.LotteryUserCampaign;
import com.omarze.entities.LotteryUserCampaignStatus;
import com.omarze.entities.Stage;
import com.omarze.exception.EntityNotFoundException;
import com.omarze.exception.ServiceException;
import com.omarze.persistence.CampaignRepository;
import com.omarze.persistence.LotteryUserCampaignRepository;
import com.omarze.services.CommandBase;
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


    private Random random = new Random(System.currentTimeMillis());

    private List<Integer> luckyIndices = new ArrayList<>();

    private List<LotteryUserCampaign> luckyUserCampaigns = new ArrayList<>();

    @NotNull(message = "Winner count cannot be empty")
    private Integer winnerCount;

    @NotNull(message = "Stage cannot be empty")
    private Stage stage;

    @NotNull(message = "Campaign ID cannot be empty")
    private Long campaignId;

    @NotNull(message = "User Campaign Repository cannot be empty")
    private LotteryUserCampaignRepository userCampaignRepository;

    @NotNull(message = "Campaign Repository cannot be empty")
    private CampaignRepository campaignRepository;



    public Evaluation setWinnerCount(Integer winnerCount) {
        this.winnerCount = winnerCount;
        return this;
    }

    public Evaluation setStage(Stage stage) {
        this.stage = stage;
        return this;
    }

    public Evaluation setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
        return this;
    }


    public Evaluation setUserCampaignRepository(LotteryUserCampaignRepository userCampaignRepository) {
        this.userCampaignRepository = userCampaignRepository;
        return this;
    }


    public Evaluation setCampaignRepository(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
        return this;
    }


    @Override
    protected CampaignStageEvaluationResult execute_() throws ServiceException {

        userCampaignRepository.updateParticipatingUserCampaignsToStatus(campaignId, LotteryUserCampaignStatus.EVALUATING);

        generateLuckyIndices();

        loadWinningUserCampaigns();

        return result();
    }


    private void generateLuckyIndices() {
        Long waitingCount = userCampaignRepository.countByCampaignStatusAndCampaign_Id(
                LotteryUserCampaignStatus.EVALUATING, campaignId
        );

        int luckyIndex;

        while (luckyIndices.size() < winnerCount) {
            luckyIndex = random.nextInt(waitingCount.intValue());

            if (luckyIndices.contains(luckyIndex)) {
                continue;
            }

            luckyIndices.add(luckyIndex);
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
                    luckyUserCampaigns.add(campaign);
                }

                ++index;
            }

        }
    }


    private CampaignStageEvaluationResult result() throws EntityNotFoundException {
        CampaignStageEvaluationResult result = new CampaignStageEvaluationResult();

        result.setStage(stage);
        result.setCampaign(
                campaignRepository
                        .findById(campaignId)
                        .orElseThrow(() -> new EntityNotFoundException("Campaign", campaignId))
        );
        result.setWinningNumbers(
                luckyUserCampaigns
                        .stream()
                        .map(LotteryUserCampaign::getUserNumber)
                        .collect(Collectors.toList())
        );

        return result;
    }



}
