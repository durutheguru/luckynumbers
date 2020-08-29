package com.julianduru.omarze.services.campaign;


import com.julianduru.omarze.annotation.PostTrigger;
import com.julianduru.omarze.api.dto.CampaignActionDTO;
import com.julianduru.omarze.api.dto.CampaignDTO;
import com.julianduru.omarze.entities.Campaign;
import com.julianduru.omarze.event.CampaignActionEvent;
import com.julianduru.omarze.event.NewCampaignEvent;
import com.julianduru.omarze.exception.ServiceException;
import com.julianduru.omarze.persistence.CampaignRepository;
import com.julianduru.omarze.persistence.PartnerRepository;
import com.julianduru.omarze.services.campaign.handlers.Approval;
import com.julianduru.omarze.services.campaign.handlers.Save;
import com.julianduru.omarze.services.campaign.handlers.UpdateCampaign;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * created by julian
 */
@Service
@RequiredArgsConstructor
public class CampaignServiceImpl implements CampaignService {


    private final CampaignRepository campaignRepository;


    private final PartnerRepository partnerRepository;


    @PostTrigger({NewCampaignEvent.class})
    public Campaign addCampaign(CampaignDTO campaignDto) throws ServiceException {
        return Save.builder()
            .campaignDto(campaignDto)
            .campaignRepository(campaignRepository)
            .partnerRepository(partnerRepository)
            .build()
            .execute();
    }


    public Campaign updateCampaign(Campaign campaign) throws ServiceException {
        return new UpdateCampaign(campaign, campaignRepository).execute();
    }


    @PostTrigger({CampaignActionEvent.class})
    public Campaign campaignAction(CampaignActionDTO campaignAction) throws ServiceException {
        return Approval.builder()
            .campaignAction(campaignAction)
            .campaignRepository(campaignRepository)
            .build()
            .execute();
    }


//    public Page<Campaign> getActiveCampaigns(Integer page, Integer size) throws ServiceException {
//        return new GetMatchingCampaign(
//                new Campaign()
//                .setCampaignStatus(CampaignStatus.ACTIVE),
//                PageRequest.of(page, size, Sort.Direction.DESC, "id")
//        ).setCampaignRepository(campaignRepository).execute();
//    }


}