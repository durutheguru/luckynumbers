package com.julianduru.omarze.services.campaign.email;


import com.julianduru.omarze.entities.LotteryUserCampaign;
import com.julianduru.omarze.entities.Stage;
import lombok.Builder;
import lombok.Data;

/**
 * created by julian
 */
@Data
@Builder
public class StageWinnerEmailRequest {


    private Stage stage;


    LotteryUserCampaign lotteryUserCampaign;


}
