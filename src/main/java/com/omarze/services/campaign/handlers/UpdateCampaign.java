package com.omarze.services.campaign.handlers;


import com.omarze.entities.Campaign;
import com.omarze.exception.InvalidUpdateException;
import com.omarze.exception.ServiceException;
import com.omarze.persistence.CampaignRepository;
import com.omarze.services.ServiceHandler;

/**
 * created by julian
 */
public class UpdateCampaign implements ServiceHandler<Campaign> {

    public final Campaign campaign;

    public final CampaignRepository campaignRepository;


    public UpdateCampaign(Campaign campaign, CampaignRepository campaignRepository) {
        this.campaign = campaign;
        this.campaignRepository = campaignRepository;
    }


    @Override
    public Campaign run() throws ServiceException {
        isValidUpdate(campaign);
        return campaignRepository.save(campaign);
    }


    private boolean isValidUpdate(Campaign campaign) throws InvalidUpdateException {
        if (campaign.getId() == null) {
            throw new InvalidUpdateException("Cannot find existing Campaign. No ID was supplied");
        }

        return true;
    }


}
