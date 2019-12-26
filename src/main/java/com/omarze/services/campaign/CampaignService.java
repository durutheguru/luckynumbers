package com.omarze.services.campaign;


import com.omarze.api.dto.CampaignApprovalDTO;
import com.omarze.api.dto.CampaignDTO;
import com.omarze.entities.Campaign;
import com.omarze.exception.ServiceException;
import com.omarze.persistence.CampaignRepository;
import com.omarze.services.campaign.handlers.Save;
import com.omarze.services.campaign.handlers.ApproveCampaign;
import com.omarze.services.campaign.handlers.UpdateCampaign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * created by julian
 */
public interface CampaignService {


    Campaign addCampaign(CampaignDTO campaignDto) throws ServiceException;


    Campaign updateCampaign(Campaign campaign) throws ServiceException;


    Campaign campaignAction(CampaignApprovalDTO campaignApproval) throws ServiceException;


}
