package com.omarze.data;


import com.omarze.dto.LotteryUserCampaignDTO;
import com.omarze.entities.Campaign;
import com.omarze.entities.LotteryUser;
import com.omarze.entities.LotteryUserCampaign;
import com.omarze.entities.LotteryUserCampaignStatus;
import com.omarze.persistence.LotteryUserCampaignRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
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
        Campaign campaign = campaignDataService.saveCampaign(
                new Campaign().setActive(true)
        );
        LotteryUser lotteryUser = lotteryUserDataService.saveLotteryUser();

        LotteryUserCampaign userCampaign = new LotteryUserCampaign();
        userCampaign.setCampaign(campaign);
        userCampaign.setLotteryUser(lotteryUser);

        return userCampaign;
    }


    public LotteryUserCampaignDTO newUserCampaignDTO() {
        LotteryUserCampaign userCampaign = newUserCampaign();
        return modelMapper.map(userCampaign, LotteryUserCampaignDTO.class);
    }


    public LotteryUserCampaign saveUserCampaign(String number) {
        LotteryUserCampaign userCampaign = newUserCampaign();
        userCampaign.setUserNumber(number);
        userCampaign.setCampaignStatus(LotteryUserCampaignStatus.WAITING);

        return userCampaignRepository.save(userCampaign);
    }


}
