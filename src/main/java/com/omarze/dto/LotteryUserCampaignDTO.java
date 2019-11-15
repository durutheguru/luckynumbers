package com.omarze.dto;


import com.omarze.entities.LotteryUserCampaignStatus;

/**
 * created by julian
 */
public class LotteryUserCampaignDTO extends BaseDTO {

    private LotteryUserDTO lotteryUser;

    private CampaignDTO campaign;

    private String userNumber;

    private LotteryUserCampaignStatus campaignStatus;


    public LotteryUserDTO getLotteryUser() {
        return lotteryUser;
    }

    public LotteryUserCampaignDTO setLotteryUser(LotteryUserDTO lotteryUser) {
        this.lotteryUser = lotteryUser;
        return this;
    }

    public CampaignDTO getCampaign() {
        return campaign;
    }

    public LotteryUserCampaignDTO setCampaign(CampaignDTO campaign) {
        this.campaign = campaign;
        return this;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public LotteryUserCampaignDTO setUserNumber(String userNumber) {
        this.userNumber = userNumber;
        return this;
    }

    public LotteryUserCampaignStatus getCampaignStatus() {
        return campaignStatus;
    }

    public LotteryUserCampaignDTO setCampaignStatus(LotteryUserCampaignStatus campaignStatus) {
        this.campaignStatus = campaignStatus;
        return this;
    }


}

