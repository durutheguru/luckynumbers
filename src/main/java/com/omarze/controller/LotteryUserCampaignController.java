package com.omarze.controller;


import com.omarze.Constants;
import com.omarze.entities.LotteryUserCampaign;
import com.omarze.exception.ServiceException;
import com.omarze.services.usercampaign.LotteryUserCampaignService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by julian
 */
@RestController
@RequestMapping(Constants.API_BASE + "/user_campaigns")
public class LotteryUserCampaignController {

    private LotteryUserCampaignService userCampaignService;


    public LotteryUserCampaignController(LotteryUserCampaignService userCampaignService) {
        this.userCampaignService = userCampaignService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LotteryUserCampaign addUserCampaign(LotteryUserCampaign userCampaign) throws ServiceException {
        return userCampaignService.addUserCampaign(userCampaign);
    }



}
