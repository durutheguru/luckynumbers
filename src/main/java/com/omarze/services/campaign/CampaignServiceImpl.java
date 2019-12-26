package com.omarze.services.campaign;


import com.omarze.api.dto.CampaignApprovalDTO;
import com.omarze.api.dto.CampaignDTO;
import com.omarze.entities.Campaign;
import com.omarze.exception.ServiceException;
import com.omarze.persistence.CampaignRepository;
import com.omarze.services.campaign.handlers.ApproveCampaign;
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


    public Campaign addCampaign(CampaignDTO campaignDto) throws ServiceException {
        return Save.builder()
                .campaignDto(campaignDto)
                .campaignRepository(campaignRepository)
                .build()
                .execute();
    }


    public Campaign updateCampaign(Campaign campaign) throws ServiceException {
        return new UpdateCampaign(campaign, campaignRepository).execute();
    }


    public Campaign campaignAction(CampaignApprovalDTO campaignApproval) throws ServiceException {
        return new ApproveCampaign(campaignApproval, campaignRepository).execute();
    }


//    public Page<Campaign> getActiveCampaigns(Integer page, Integer size) throws ServiceException {
//        return new GetMatchingCampaign(
//                new Campaign()
//                .setCampaignStatus(CampaignStatus.ACTIVE),
//                PageRequest.of(page, size, Sort.Direction.DESC, "id")
//        ).setCampaignRepository(campaignRepository).execute();
//    }


}
