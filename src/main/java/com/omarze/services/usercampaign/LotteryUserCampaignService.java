package com.omarze.services.usercampaign;


import com.omarze.entities.LotteryUserCampaign;
import com.omarze.exception.ServiceException;
import com.omarze.persistence.CampaignRepository;
import com.omarze.persistence.LotteryUserCampaignRepository;
import com.omarze.persistence.LotteryUserRepository;
import com.omarze.services.campaign.NumberGenerator;
import com.omarze.services.usercampaign.handlers.AddLotteryUserCampaign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * created by julian
 */
@Service
public class LotteryUserCampaignService {

    @Autowired
    private LotteryUserCampaignRepository userCampaignRepository;

    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private LotteryUserRepository lotteryUserRepository;

    @Autowired
    private NumberGenerator numberGenerator;


    public LotteryUserCampaign addUserCampaign(LotteryUserCampaign userCampaign) throws ServiceException {
        return new AddLotteryUserCampaign(userCampaign)
                .userCampaignRepository(userCampaignRepository)
                .numberGenerator(numberGenerator)
                .campaignRepository(campaignRepository)
                .lotteryUserRepository(lotteryUserRepository)
                .run();
    }


}
