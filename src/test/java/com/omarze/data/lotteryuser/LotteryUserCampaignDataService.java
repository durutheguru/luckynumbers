package com.omarze.data.lotteryuser;


import com.julianduru.util.RandomGenerator;
import com.omarze.data.campaign.CampaignDataProvider;
import com.omarze.api.dto.LotteryUserCampaignDTO;
import com.omarze.entities.*;
import com.omarze.persistence.LotteryUserCampaignRepository;
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


    final CampaignDataProvider campaignDataProvider;


    final LotteryUserDataProvider lotteryUserDataProvider;


    final LotteryUserCampaignRepository userCampaignRepository;


    public LotteryUserCampaignDataService(ModelMapper modelMapper, CampaignDataProvider campaignDataProvider, LotteryUserDataProvider lotteryUserDataProvider, LotteryUserCampaignRepository userCampaignRepository) {
        this.modelMapper = modelMapper;
        this.campaignDataProvider = campaignDataProvider;
        this.lotteryUserDataProvider = lotteryUserDataProvider;
        this.userCampaignRepository = userCampaignRepository;
    }


    public LotteryUserCampaign newUserCampaign() {
        Campaign campaign = new Campaign();
        campaign.setCampaignStatus(CampaignStatus.APPROVED);

        return newUserCampaign(campaign);
    }


    public LotteryUserCampaign newUserCampaign(Campaign campaign) {
        LotteryUser lotteryUser = lotteryUserDataProvider.saveLotteryUser();

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
