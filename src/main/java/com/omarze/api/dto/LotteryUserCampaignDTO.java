package com.omarze.api.dto;


import com.omarze.entities.LotteryUserCampaignStatus;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * created by julian
 */
@Data
public class LotteryUserCampaignDTO extends BaseDTO {


    @NotNull(message = "Lottery User ID is required")
    private Long lotteryUserId;


    private LotteryUserDTO lotteryUser;


    @NotNull(message = "Campaign ID is required")
    private Long campaignId;


    private CampaignDTO campaign;


    private String userNumber;


    private LotteryUserCampaignStatus campaignStatus;


}

