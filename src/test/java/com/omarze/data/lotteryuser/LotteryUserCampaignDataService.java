package com.omarze.data.lotteryuser;


import com.omarze.data.campaign.CampaignDataService;
import com.omarze.api.dto.LotteryUserCampaignDTO;
import com.omarze.entities.*;
import com.omarze.persistence.LotteryUserCampaignRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * created by julian
 */
@Component
public class LotteryUserCampaignDataService {


    final ModelMapper modelMapper;


    final CampaignDataService campaignDataService;


    final LotteryUserDataService lotteryUserDataService;


    final LotteryUserCampaignRepository userCampaignRepository;


    public LotteryUserCampaignDataService(ModelMapper modelMapper, CampaignDataService campaignDataService, LotteryUserDataService lotteryUserDataService, LotteryUserCampaignRepository userCampaignRepository) {
        this.modelMapper = modelMapper;
        this.campaignDataService = campaignDataService;
        this.lotteryUserDataService = lotteryUserDataService;
        this.userCampaignRepository = userCampaignRepository;
    }


    public LotteryUserCampaign newUserCampaign() {
        Campaign campaign = campaignDataService.saveCampaign(Campaign.builder().campaignStatus(CampaignStatus.APPROVED).build());
        LotteryUser lotteryUser = lotteryUserDataService.saveLotteryUser();

        LotteryUserCampaign userCampaign = new LotteryUserCampaign();
        userCampaign.setCampaign(campaign);
        userCampaign.setLotteryUser(lotteryUser);

        return userCampaign;
    }


    public LotteryUserCampaignDTO newUserCampaignDTO() {
        LotteryUserCampaign userCampaign = newUserCampaign();

        LotteryUserCampaignDTO userCampaignDTO = new LotteryUserCampaignDTO();

        userCampaignDTO.setCampaignId(userCampaign.getCampaign().getId());
        userCampaignDTO.setLotteryUserId(userCampaign.getLotteryUser().getId());

        return userCampaignDTO;
    }


    public LotteryUserCampaign saveUserCampaign(String number) {
        LotteryUserCampaign userCampaign = newUserCampaign();
        userCampaign.setUserNumber(number);
        userCampaign.setCampaignStatus(LotteryUserCampaignStatus.WAITING);

        return userCampaignRepository.save(userCampaign);
    }


}
