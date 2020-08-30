package com.julianduru.omarze.controller.api;


import com.julianduru.util.MapperUtil;
import com.julianduru.Constants;
import com.julianduru.omarze.api.dto.CampaignDTO;
import com.julianduru.omarze.entities.Campaign;
import com.julianduru.omarze.exception.ServiceException;
import com.julianduru.omarze.security.annotation.CanWriteCampaign;
import com.julianduru.omarze.services.campaign.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * created by julian
 */
@RestController
@RequestMapping(CampaignController.PATH)
public class CampaignController extends BaseApiController {


    public static final String PATH = Constants.API_BASE + "/campaign_request";



    private CampaignService campaignService;


    @Autowired
    public CampaignController setCampaignService(CampaignService campaignService) {
        this.campaignService = campaignService;
        return this;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CanWriteCampaign
    public CampaignDTO addCampaign(
            @Valid @RequestBody CampaignDTO campaignDto
    ) throws ServiceException {
        Campaign campaign = campaignService.addCampaign(campaignDto);
        return MapperUtil.map(campaign, CampaignDTO.class);
    }


    @PutMapping
    @CanWriteCampaign
    public Campaign updateCampaign(
            @RequestBody Campaign campaign
    ) throws ServiceException {
        return campaignService.updateCampaign(campaign);
    }


}