package com.omarze.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

/**
 * created by julian
 */
@Entity
public class LotteryUserCampaign extends BaseEntity {


    @ManyToOne
    @JoinColumn(nullable = false)
    private LotteryUser lotteryUser;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Campaign campaign;

    @NotEmpty(message = "Lottery User Number cannot be empty")
    @Column(nullable = false, length = 100)
    private String userNumber;

    @Column(nullable = false, length = 50)
    private LotteryUserCampaignStatus campaignStatus;


    public LotteryUser getLotteryUser() {
        return lotteryUser;
    }

    public LotteryUserCampaign setLotteryUser(LotteryUser lotteryUser) {
        this.lotteryUser = lotteryUser;
        return this;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public LotteryUserCampaign setCampaign(Campaign campaign) {
        this.campaign = campaign;
        return this;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public LotteryUserCampaign setUserNumber(String userNumber) {
        this.userNumber = userNumber;
        return this;
    }

    public LotteryUserCampaignStatus getCampaignStatus() {
        return campaignStatus;
    }

    public LotteryUserCampaign setCampaignStatus(LotteryUserCampaignStatus campaignStatus) {
        this.campaignStatus = campaignStatus;
        return this;
    }


}
