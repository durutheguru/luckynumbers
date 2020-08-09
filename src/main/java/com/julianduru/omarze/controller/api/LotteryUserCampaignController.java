package com.julianduru.omarze.controller.api;


import com.julianduru.util.MapperUtil;
import com.julianduru.Constants;
import com.julianduru.omarze.api.dto.LotteryUserCampaignDTO;
import com.julianduru.omarze.entities.LotteryUserCampaign;
import com.julianduru.omarze.exception.ServiceException;
import com.julianduru.omarze.security.annotation.IsLotteryUser;
import com.julianduru.omarze.services.usercampaign.LotteryUserCampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * created by julian
 */
@RestController
@RequestMapping(LotteryUserCampaignController.PATH)
public class LotteryUserCampaignController extends BaseApiController {

    public static final String PATH = Constants.API_BASE + "/user_campaign";


    private LotteryUserCampaignService userCampaignService;


    @Autowired
    public void setUserCampaignService(LotteryUserCampaignService userCampaignService) {
        this.userCampaignService = userCampaignService;
    }


    @PostMapping
    @IsLotteryUser
    @ResponseStatus(HttpStatus.CREATED)
    public LotteryUserCampaignDTO addUserCampaign(
            @Valid @RequestBody LotteryUserCampaignDTO userCampaignDTO
    ) throws ServiceException {
        LotteryUserCampaign userCampaign = userCampaignService.addUserCampaign(userCampaignDTO);
        return MapperUtil.map(userCampaign, LotteryUserCampaignDTO.class);
    }


}

