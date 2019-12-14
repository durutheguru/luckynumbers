package com.omarze.services.campaign;


import com.omarze.api.dto.CampaignApprovalDTO;
import com.omarze.entities.Campaign;
import com.omarze.entities.CampaignStatus;
import com.omarze.exception.ServiceException;
import com.omarze.persistence.CampaignRepository;
import com.omarze.services.campaign.handlers.AddCampaign;
import com.omarze.services.campaign.handlers.ApproveCampaign;
import com.omarze.services.campaign.handlers.GetMatchingCampaign;
import com.omarze.services.campaign.handlers.UpdateCampaign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * created by julian
 */
@Service
public class CampaignService {


    private CampaignRepository campaignRepository;


    @Autowired
    public CampaignService setCampaignRepository(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
        return this;
    }


    public Campaign addCampaign(Campaign campaign) throws ServiceException {
        return new AddCampaign(campaign, campaignRepository).run();
    }


    public Campaign updateCampaign(Campaign campaign) throws ServiceException {
        return new UpdateCampaign(campaign, campaignRepository).run();
    }


    public Campaign campaignAction(CampaignApprovalDTO campaignApproval) throws ServiceException {
        return new ApproveCampaign(campaignApproval, campaignRepository).run();
    }


//    public Page<Campaign> getActiveCampaigns(Integer page, Integer size) throws ServiceException {
//        return new GetMatchingCampaign(
//                new Campaign()
//                .setCampaignStatus(CampaignStatus.ACTIVE),
//                PageRequest.of(page, size, Sort.Direction.DESC, "id")
//        ).setCampaignRepository(campaignRepository).run();
//    }


}
