package com.omarze.services.usercampaign;


import com.omarze.api.dto.LotteryUserCampaignDTO;
import com.omarze.entities.LotteryUserCampaign;
import com.omarze.exception.ServiceException;

/**
 * created by julian
 */
public interface LotteryUserCampaignService {


    LotteryUserCampaign addUserCampaign(LotteryUserCampaignDTO userCampaignDTO) throws ServiceException ;


}


