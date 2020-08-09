package com.julianduru.omarze.data.lotteryuser;


import com.julianduru.omarze.entities.*;
import com.julianduru.omarze.persistence.CampaignRepository;
import com.julianduru.util.RandomGenerator;
import com.julianduru.omarze.data.campaign.CampaignDataProvider;
import com.julianduru.omarze.api.dto.LotteryUserCampaignDTO;
import com.julianduru.omarze.persistence.LotteryUserCampaignRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * created by julian
 */
@Component
@RequiredArgsConstructor
public class LotteryUserCampaignDataService {


    final ModelMapper modelMapper;


    final CampaignDataProvider campaignDataProvider;


    final LotteryUserDataProvider lotteryUserDataProvider;


    final LotteryUserCampaignRepository userCampaignRepository;



    public LotteryUserCampaign newUserCampaign() {
        return newUserCampaign(campaignDataProvider.saveActiveCampaign());
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

