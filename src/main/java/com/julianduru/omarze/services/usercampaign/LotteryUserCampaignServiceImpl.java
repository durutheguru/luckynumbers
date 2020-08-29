package com.julianduru.omarze.services.usercampaign;


import com.julianduru.omarze.annotation.PostTrigger;
import com.julianduru.omarze.api.dto.LotteryUserCampaignDTO;
import com.julianduru.omarze.entities.LotteryUserCampaign;
import com.julianduru.omarze.event.LotteryUserCampaignEvent;
import com.julianduru.omarze.exception.ServiceException;
import com.julianduru.omarze.persistence.CampaignRepository;
import com.julianduru.omarze.persistence.LotteryUserCampaignRepository;
import com.julianduru.omarze.persistence.LotteryUserRepository;
import com.julianduru.omarze.services.campaign.NumberGenerator;
import com.julianduru.omarze.services.usercampaign.handlers.Save;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * created by julian
 */
@Service
@RequiredArgsConstructor
public class LotteryUserCampaignServiceImpl implements LotteryUserCampaignService {


    private final LotteryUserCampaignRepository userCampaignRepository;


    private final CampaignRepository campaignRepository;


    private final LotteryUserRepository lotteryUserRepository;


    private final NumberGenerator numberGenerator;


    @Override
    @PostTrigger({LotteryUserCampaignEvent.class})
    public LotteryUserCampaign addUserCampaign(LotteryUserCampaignDTO userCampaignDTO) throws ServiceException {
        return Save.builder()
            .userCampaignDTO(userCampaignDTO)
            .userCampaignRepository(userCampaignRepository)
            .numberGenerator(numberGenerator)
            .campaignRepository(campaignRepository)
            .lotteryUserRepository(lotteryUserRepository)
            .build()
            .execute();
    }


}

