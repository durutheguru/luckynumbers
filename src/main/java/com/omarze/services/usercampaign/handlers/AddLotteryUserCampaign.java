package com.omarze.services.usercampaign.handlers;


import com.omarze.entities.Campaign;
import com.omarze.entities.LotteryUser;
import com.omarze.entities.LotteryUserCampaign;
import com.omarze.entities.LotteryUserCampaignStatus;
import com.omarze.exception.*;
import com.omarze.persistence.CampaignRepository;
import com.omarze.persistence.LotteryUserCampaignRepository;
import com.omarze.persistence.LotteryUserRepository;
import com.omarze.services.AbstractServiceHandler;
import com.omarze.services.ServiceHandler;
import com.omarze.services.campaign.NumberGenerator;
import com.omarze.util.ValidatorUtil;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * created by julian
 */
public class AddLotteryUserCampaign extends AbstractServiceHandler<LotteryUserCampaign> {

    @NotNull(message = "User Campaign Must be initialized.")
    private LotteryUserCampaign userCampaign;

    @NotNull(message = "User Campaign Repository Must be initialized")
    private LotteryUserCampaignRepository userCampaignRepository;

    @NotNull(message = "Campaign Repository Must be initialized")
    private CampaignRepository campaignRepository;

    @NotNull(message = "Lottery User Repository Must be initialized")
    private LotteryUserRepository lotteryUserRepository;

    @NotNull(message = "Number Generator Must be initialized")
    private NumberGenerator numberGenerator;


    private Long campaignId;

    private Long lotteryUserId;

    private Campaign campaign;

    private LotteryUser lotteryUser;


    public AddLotteryUserCampaign(LotteryUserCampaign userCampaign) {
        Assert.notNull(userCampaign, "User Campaign should not be Null");

        this.userCampaign = userCampaign;
        this.campaignId = this.userCampaign.getCampaign().getId();
        this.lotteryUserId = this.userCampaign.getLotteryUser().getId();
    }


    public AddLotteryUserCampaign userCampaignRepository(LotteryUserCampaignRepository userCampaignRepository) {
        this.userCampaignRepository = userCampaignRepository;
        return this;
    }


    public AddLotteryUserCampaign numberGenerator(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
        return this;
    }


    public AddLotteryUserCampaign lotteryUserRepository(LotteryUserRepository lotteryUserRepository) {
        this.lotteryUserRepository = lotteryUserRepository;
        return this;
    }


    public AddLotteryUserCampaign campaignRepository(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
        return this;
    }


    @Override
    protected void preExecute() throws ServiceException {
        verifyCampaignStatus();
        verifyUserCampaignDoesNotExist();
    }


    private void verifyCampaignStatus() throws InactiveCampaignException {
        Boolean activeCampaign = campaignRepository.isCampaignActive(campaignId);
        if (activeCampaign == null || !activeCampaign) {
            throw new InactiveCampaignException(campaignId);
        }
    }


    private void verifyUserCampaignDoesNotExist() throws DuplicateUserCampaignException {
        Optional<LotteryUserCampaign> existingUserCampaign = userCampaignRepository.findByLotteryUser_IdAndCampaign_Id(
                lotteryUserId, campaignId
        );

        if (existingUserCampaign.isPresent()) {
            throw new DuplicateUserCampaignException(lotteryUserId, campaignId);
        }
    }


    @Override
    protected LotteryUserCampaign execute() throws ServiceException {
        loadCampaign();
        loadLotteryUser();

        return saveLotteryUserCampaign();
    }


    private void loadCampaign() throws EntityNotFoundException {
        Optional<Campaign> campaignOptional = campaignRepository.findById(campaignId);
        if (!campaignOptional.isPresent()) {
            throw new EntityNotFoundException("Campaign", userCampaign.getCampaign().getId());
        }

        campaign = campaignOptional.get();
    }


    private void loadLotteryUser() throws EntityNotFoundException {
        Optional<LotteryUser> lotteryUserOptional = lotteryUserRepository.findById(lotteryUserId);
        if (!lotteryUserOptional.isPresent()) {
            throw new EntityNotFoundException("Lottery User", userCampaign.getLotteryUser().getId());
        }

        lotteryUser = lotteryUserOptional.get();
    }


    private LotteryUserCampaign saveLotteryUserCampaign() {
        LotteryUserCampaign userCampaign = new LotteryUserCampaign();
        userCampaign.setCampaign(campaign);
        userCampaign.setLotteryUser(lotteryUser);
        userCampaign.setUserNumber(numberGenerator.generateUniqueUserNumber(campaign));
        userCampaign.setCampaignStatus(LotteryUserCampaignStatus.WAITING);

        return userCampaignRepository.save(userCampaign);
    }


}
