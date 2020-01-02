package com.omarze.services.campaign;


import com.omarze.api.dto.CampaignActionDTO;
import com.omarze.api.dto.CampaignDTO;
import com.omarze.entities.Campaign;
import com.omarze.exception.ServiceException;

/**
 * created by julian
 */
public interface CampaignService {


    Campaign addCampaign(CampaignDTO campaignDto) throws ServiceException;


    Campaign updateCampaign(Campaign campaign) throws ServiceException;


    Campaign campaignAction(CampaignActionDTO campaignAction) throws ServiceException;


}
