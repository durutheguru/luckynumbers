package com.omarze.controller;


import com.omarze.Constants;
import com.omarze.entities.Campaign;
import com.omarze.exception.ServiceException;
import com.omarze.services.campaign.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * created by julian
 */
@RestController
@RequestMapping(Constants.API_V1_BASE + "/campaigns")
public class CampaignController {


    private CampaignService campaignService;


    @Autowired
    public CampaignController setCampaignService(CampaignService campaignService) {
        this.campaignService = campaignService;
        return this;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Campaign addCampaign(Campaign campaign) throws ServiceException {
        return campaignService.addCampaign(campaign);
    }


    @PutMapping
    public Campaign updateCampaign(Campaign campaign) throws ServiceException {
        return campaignService.updateCampaign(campaign);
    }


}
