package com.omarze.services.campaign;


import com.omarze.annotation.PostTrigger;
import com.omarze.api.dto.CampaignActionDTO;
import com.omarze.api.dto.CampaignDTO;
import com.omarze.entities.Campaign;
import com.omarze.event.CampaignActionEvent;
import com.omarze.exception.ServiceException;
import com.omarze.persistence.CampaignRepository;
import com.omarze.persistence.PartnerRepository;
import com.omarze.services.campaign.handlers.Approval;
import com.omarze.services.campaign.handlers.Save;
import com.omarze.services.campaign.handlers.UpdateCampaign;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * created by julian
 */
@Service
@RequiredArgsConstructor
public class CampaignServiceImpl implements CampaignService {


    private final CampaignRepository campaignRepository;


    private final PartnerRepository partnerRepository;


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

