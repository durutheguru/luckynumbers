package com.omarze.data.lotteryuser;


import com.omarze.data.campaign.CampaignDataService;
import com.omarze.api.dto.LotteryUserCampaignDTO;
import com.omarze.entities.*;
import com.omarze.persistence.LotteryUserCampaignRepository;
import com.omarze.util.RandomGenerator;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
        return newUserCampaign(campaign);
    }


    public LotteryUserCampaign newUserCampaign(Campaign campaign) {
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


    public LotteryUserCampaign saveUserCampaign(Campaign campaign) {
        LotteryUserCampaign userCampaign = newUserCampaign(campaign);
        userCampaign.setUserNumber(RandomGenerator.randomStringNumber(10));
        userCampaign.setCampaignStatus(LotteryUserCampaignStatus.WAITING);

        return userCampaignRepository.save(userCampaign);
    }


    public List<LotteryUserCampaign> saveUserCampaigns(Campaign campaign, int count) {
        List<LotteryUserCampaign> userCampaigns = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            userCampaigns.add(saveUserCampaign(campaign));
        }

        return userCampaigns;
    }





}
