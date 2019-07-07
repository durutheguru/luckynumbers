package com.omarze.controller;


import com.omarze.Constants;
import com.omarze.dto.CampaignApprovalDTO;
import com.omarze.entities.Campaign;
import com.omarze.exception.ServiceException;
import com.omarze.services.campaign.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by julian
 */
@RestController
@RequestMapping(Constants.API_V1_BASE + "/campaign_approvals")
public class CampaignApprovalController {


    private CampaignService campaignService;


    @Autowired
    public CampaignApprovalController setCampaignService(CampaignService campaignService) {
        this.campaignService = campaignService;
        return this;
    }


    @PostMapping
    public Campaign approval(@RequestBody CampaignApprovalDTO approvalDTO) throws ServiceException {
        return campaignService.campaignAction(approvalDTO);
    }


}
