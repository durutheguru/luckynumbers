package com.omarze.controller.api;


import com.omarze.Constants;
import com.omarze.api.dto.CampaignActionDTO;
import com.omarze.exception.ServiceException;
import com.omarze.security.annotation.IsBackOfficeUser;
import com.omarze.services.campaign.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * created by julian
 */
@RestController
@RequestMapping(CampaignApprovalController.PATH)
public class CampaignApprovalController extends BaseApiController {

    public static final String PATH = Constants.API_BASE + "/campaign_approval";


    private CampaignService campaignService;


    @Autowired
    public CampaignApprovalController setCampaignService(CampaignService campaignService) {
        this.campaignService = campaignService;
        return this;
    }


    @PostMapping
    @IsBackOfficeUser
    public void approval(@Valid @RequestBody CampaignActionDTO actionDTO) throws ServiceException {
        campaignService.campaignAction(actionDTO);
    }


}
