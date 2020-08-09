package com.julianduru.omarze.services.usercampaign;


import com.julianduru.omarze.api.dto.LotteryUserCampaignDTO;
import com.julianduru.omarze.entities.LotteryUserCampaign;
import com.julianduru.omarze.exception.ServiceException;

/**
 * created by julian
 */
public interface LotteryUserCampaignService {


    LotteryUserCampaign addUserCampaign(LotteryUserCampaignDTO userCampaignDTO) throws ServiceException ;


}


