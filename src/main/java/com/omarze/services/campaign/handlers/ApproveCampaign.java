package com.omarze.services.campaign.handlers;


import com.omarze.dto.CampaignApprovalDTO;
import com.omarze.entities.Campaign;
import com.omarze.entities.CampaignStatus;
import com.omarze.entities.RequestStatus;
import com.omarze.exception.EntityNotFoundException;
import com.omarze.exception.InvalidUpdateException;
import com.omarze.exception.ServiceException;
import com.omarze.model.ApprovalAction;
import com.omarze.persistence.CampaignRepository;
import com.omarze.services.ServiceHandler;

import java.util.Optional;

/**
 * created by julian
 */
public class ApproveCampaign implements ServiceHandler<Campaign> {

    private Campaign campaign;

    private final CampaignApprovalDTO campaignApproval;

    private final CampaignRepository campaignRepository;


    public ApproveCampaign(CampaignApprovalDTO campaignApproval, CampaignRepository campaignRepository) {
        this.campaignApproval = campaignApproval;
        this.campaignRepository = campaignRepository;
    }


    @Override
    public Campaign run() throws ServiceException {
        campaign = getCampaignEntity();

        ApprovalAction action = campaignApproval.getApprovalAction();

        validateCampaignState();
        campaign.setRequestStatus(RequestStatus.fromApproval(action));
        campaign.setCampaignStatus(CampaignStatus.fromApproval(action));

        return campaignRepository.save(campaign);
    }


    public void validateCampaignState() throws InvalidUpdateException {
        if (!campaign.canBeApproved()) {
            throw new InvalidUpdateException("Campaign cannot be updated.");
        }
    }


    private Campaign getCampaignEntity() throws EntityNotFoundException {
        Long campaignId = campaignApproval.getCampaignId();
        Optional<Campaign> campaign = campaignRepository.findById(campaignId);

        if (!campaign.isPresent()) {
            throw new EntityNotFoundException("Campaign", campaignId);
        }

        return campaign.get();
    }


}
