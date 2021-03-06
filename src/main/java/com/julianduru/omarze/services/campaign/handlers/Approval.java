package com.julianduru.omarze.services.campaign.handlers;


import com.julianduru.security.Auth;
import com.julianduru.security.entity.UserAuthId;
import com.julianduru.omarze.api.dto.CampaignActionDTO;
import com.julianduru.omarze.entities.Campaign;
import com.julianduru.omarze.entities.CampaignStatus;
import com.julianduru.omarze.exception.EntityNotFoundException;
import com.julianduru.omarze.exception.InvalidUpdateException;
import com.julianduru.omarze.exception.ServiceException;
import com.julianduru.omarze.model.ApprovalAction;
import com.julianduru.omarze.persistence.CampaignRepository;
import com.julianduru.omarze.services.CommandBase;
import lombok.Builder;

import java.util.Objects;
import java.util.Optional;

/**
 * created by julian
 */
public class Approval extends CommandBase<Campaign> {


    private Campaign campaign;


    private final CampaignActionDTO campaignAction;


    private final CampaignRepository campaignRepository;


    @Builder
    private Approval(CampaignActionDTO campaignAction, CampaignRepository campaignRepository) {
        this.campaignAction = campaignAction;
        this.campaignRepository = campaignRepository;
    }


    @Override
    public Campaign execute_() throws ServiceException {
        validateAction();
        setCampaignAction();

        return saveCampaign();
    }


    private void validateAction() throws EntityNotFoundException, InvalidUpdateException {
        validateCampaignApprovalState();
        validateApproverIsNotCreator();
    }


    private void validateCampaignApprovalState() throws EntityNotFoundException, InvalidUpdateException {
        campaign = getCampaignEntity();

        if (!campaign.canBeApproved()) {
            throw new InvalidUpdateException("Campaign cannot be updated. Must be awaiting Approval");
        }
    }


    private void validateApproverIsNotCreator() throws InvalidUpdateException {
        UserAuthId creator = campaign.getCreatedBy();
        UserAuthId currentUser = Auth.getUserAuthId(true);
        if (creator == null || currentUser == null) {
            return;
        }

        if (Objects.equals(currentUser.authUsername, creator.authUsername)) {
            throw new InvalidUpdateException("User who created campaign is not allowed to approve");
        }
    }


    private void setCampaignAction() {
        ApprovalAction action = campaignAction.getApprovalAction();
        campaign.setCampaignStatus(CampaignStatus.fromApproval(action));
    }


    private Campaign getCampaignEntity() throws EntityNotFoundException {
        Long campaignId = campaignAction.getCampaignId();
        Optional<Campaign> campaign = campaignRepository.findById(campaignId);

        if (!campaign.isPresent()) {
            throw new EntityNotFoundException("Campaign", campaignId);
        }

        return campaign.get();
    }


    private Campaign saveCampaign() {
        return campaignRepository.save(campaign);
    }


}

