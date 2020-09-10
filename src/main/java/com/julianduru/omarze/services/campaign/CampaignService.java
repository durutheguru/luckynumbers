package com.julianduru.omarze.services.campaign;


import com.julianduru.omarze.api.dto.CampaignActionDTO;
import com.julianduru.omarze.api.dto.CampaignDTO;
import com.julianduru.omarze.entities.Campaign;
import com.julianduru.omarze.exception.ServiceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * created by julian
 */
public interface CampaignService {


    Campaign addCampaign(CampaignDTO campaignDto) throws ServiceException;


    Campaign updateCampaign(Campaign campaign) throws ServiceException;


    Campaign campaignAction(CampaignActionDTO campaignAction) throws ServiceException;


}