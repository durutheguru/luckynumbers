package com.julianduru.omarze.services.usercampaign.handlers;


import com.julianduru.omarze.api.dto.LotteryUserCampaignDTO;
import com.julianduru.omarze.entities.*;
import com.julianduru.omarze.exception.DuplicateUserCampaignException;
import com.julianduru.omarze.exception.EntityNotFoundException;
import com.julianduru.omarze.exception.ServiceException;
import com.julianduru.omarze.persistence.CampaignRepository;
import com.julianduru.omarze.persistence.LotteryUserCampaignRepository;
import com.julianduru.omarze.persistence.LotteryUserRepository;
import com.julianduru.omarze.services.CommandBase;
import com.julianduru.omarze.services.campaign.NumberGenerator;
import com.julianduru.security.Auth;
import com.julianduru.security.entity.UserAuthId;
import lombok.Builder;

import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * created by julian
 */
public class Save extends CommandBase<LotteryUserCampaign> {


    @NotNull(message = "User Campaign Must be initialized.")
    private LotteryUserCampaignDTO userCampaignDTO;


    @NotNull(message = "User Campaign Repository Must be initialized")
    private LotteryUserCampaignRepository userCampaignRepository;


    @NotNull(message = "Campaign Repository Must be initialized")
    private CampaignRepository campaignRepository;


    @NotNull(message = "Lottery User Repository Must be initialized")
    private LotteryUserRepository lotteryUserRepository;


    @NotNull(message = "Number Generator Must be initialized")
    private NumberGenerator numberGenerator;


    private Campaign campaign;


    private LotteryUser lotteryUser;


    @Builder
    public Save(
        LotteryUserCampaignDTO userCampaignDTO,
        LotteryUserCampaignRepository userCampaignRepository,
        CampaignRepository campaignRepository,
        LotteryUserRepository lotteryUserRepository,
        NumberGenerator numberGenerator
    ) {
        this.userCampaignDTO = userCampaignDTO;
        this.userCampaignRepository = userCampaignRepository;
        this.campaignRepository = campaignRepository;
        this.lotteryUserRepository = lotteryUserRepository;
        this.numberGenerator = numberGenerator;
    }


    private void verifyCampaignStatus() throws ServiceException {
        if (campaign.getCampaignStatus() != CampaignStatus.ACTIVE) {
            throw new ServiceException("Campaign is not Active");
        }
    }


    private void verifyUserCampaignDoesNotExist() throws DuplicateUserCampaignException {
        Optional<LotteryUserCampaign> existingUserCampaign = userCampaignRepository.findByLotteryUser_IdAndCampaign_Id(
                lotteryUser.getId(), campaign.getId()
        );

        if (existingUserCampaign.isPresent()) {
            throw new DuplicateUserCampaignException(userCampaignDTO.getLotteryUserId(), userCampaignDTO.getCampaignId());
        }
    }


    @Override
    protected LotteryUserCampaign execute_() throws ServiceException {
        loadCampaign();
        loadLotteryUser();

        verifyCampaignStatus();
        verifyUserCampaignDoesNotExist();

        return saveLotteryUserCampaign();
    }


    private void loadCampaign() throws EntityNotFoundException {
        Optional<Campaign> campaignOptional = campaignRepository.findById(userCampaignDTO.getCampaignId());
        campaign = campaignOptional.orElseThrow(() -> new EntityNotFoundException("Campaign", userCampaignDTO.getCampaignId()));
    }


    private void loadLotteryUser() throws EntityNotFoundException {
        UserAuthId authId = Auth.getUserAuthId(true);

        Optional<LotteryUser> lotteryUserOptional = lotteryUserRepository.findByUsername(authId.authUsername);
        lotteryUser = lotteryUserOptional.orElseThrow(
            () -> new EntityNotFoundException("Lottery Username", authId.authUsername)
        );
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


