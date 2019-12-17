package com.omarze.api.dto;


import com.omarze.entities.LotteryUserCampaignStatus;
import lombok.Data;

/**
 * created by julian
 */
@Data
public class LotteryUserCampaignDTO extends BaseDTO {


    private LotteryUserDTO lotteryUser;


    private CampaignDTO campaign;


    private String userNumber;


    private LotteryUserCampaignStatus campaignStatus;


}

