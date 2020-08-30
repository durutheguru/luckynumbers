package com.julianduru.omarze.services.campaign.handlers;


import com.julianduru.omarze.entities.Campaign;
import com.julianduru.omarze.exception.InvalidUpdateException;
import com.julianduru.omarze.exception.ServiceException;
import com.julianduru.omarze.persistence.CampaignRepository;
import com.julianduru.omarze.services.Command;

/**
 * created by julian
 */
public class UpdateCampaign implements Command<Campaign> {

    public final Campaign campaign;

    public final CampaignRepository campaignRepository;


    public UpdateCampaign(Campaign campaign, CampaignRepository campaignRepository) {
        this.campaign = campaign;
        this.campaignRepository = campaignRepository;
    }


    @Override
    public Campaign execute() throws ServiceException {
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
