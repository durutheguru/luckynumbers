package com.omarze.controller;


import com.omarze.Constants;
import com.omarze.api.dto.CampaignDTO;
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
@RequestMapping(Constants.API_BASE + "/campaigns")
public class CampaignController extends ApiBaseController {


    private CampaignService campaignService;


    @Autowired
    public CampaignController setCampaignService(CampaignService campaignService) {
        this.campaignService = campaignService;
        return this;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CampaignDTO addCampaign(
            @RequestBody CampaignDTO campaignDto
    ) throws ServiceException {
        Campaign campaign = campaignService.addCampaign(campaignDto);
        return map(campaign, CampaignDTO.class);
    }


    @PutMapping
    public Campaign updateCampaign(
            @RequestBody Campaign campaign
    ) throws ServiceException {
        return campaignService.updateCampaign(campaign);
    }


}
