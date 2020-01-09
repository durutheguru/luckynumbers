package com.omarze.services.usercampaign;


import com.omarze.annotation.PostTrigger;
import com.omarze.api.dto.LotteryUserCampaignDTO;
import com.omarze.entities.LotteryUserCampaign;
import com.omarze.event.LotteryUserCampaignEvent;
import com.omarze.exception.ServiceException;
import com.omarze.persistence.CampaignRepository;
import com.omarze.persistence.LotteryUserCampaignRepository;
import com.omarze.persistence.LotteryUserRepository;
import com.omarze.services.campaign.NumberGenerator;
import com.omarze.services.usercampaign.handlers.Save;
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
