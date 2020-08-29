package com.julianduru.omarze.api.dto;


import com.julianduru.omarze.entities.LotteryUserCampaignStatus;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * created by julian
 */
@Data
public class LotteryUserCampaignDTO extends BaseDTO {


    private Long lotteryUserId;


    private LotteryUserDTO lotteryUser;


    @NotNull(message = "Campaign ID is required")
    private Long campaignId;


    private CampaignDTO campaign;


    private String userNumber;


    private LotteryUserCampaignStatus campaignStatus;


}

