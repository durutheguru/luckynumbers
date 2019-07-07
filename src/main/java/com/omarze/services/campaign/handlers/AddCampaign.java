package com.omarze.services.campaign.handlers;


import com.omarze.entities.Campaign;
import com.omarze.entities.RequestStatus;
import com.omarze.exception.ServiceException;
import com.omarze.persistence.CampaignRepository;
import com.omarze.services.ServiceHandler;
import com.omarze.util.ValidatorUtil;

/**
 * created by julian
 */
public class AddCampaign implements ServiceHandler<Campaign> {

    private final Campaign campaign;

    private final CampaignRepository campaignRepository;


    public AddCampaign(Campaign campaign, CampaignRepository campaignRepository) {
        this.campaign = campaign;
        this.campaignRepository = campaignRepository;
    }


    @Override
    public Campaign run() throws ServiceException {
        ValidatorUtil.validate(campaign);
        campaign.setRequestStatus(RequestStatus.PENDING);
        return campaignRepository.save(campaign);
    }


}
