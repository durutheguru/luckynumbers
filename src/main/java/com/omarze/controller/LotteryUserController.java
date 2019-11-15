package com.omarze.controller;


import com.omarze.Constants;
import com.omarze.entities.LotteryUser;
import com.omarze.exception.ServiceException;
import com.omarze.services.lotteryuser.LotteryUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by julian
 */
@RestController
@RequestMapping(Constants.API_V1_BASE + "/lottery_users")
public class LotteryUserController {


    private LotteryUserService lotteryUserService;


    @Autowired
    public LotteryUserController setLotteryUserService(LotteryUserService lotteryUserService) {
        this.lotteryUserService = lotteryUserService;
        return this;
    }


    @PostMapping("/sign_up")
    @ResponseStatus(HttpStatus.CREATED)
    public LotteryUser addLotteryUser(LotteryUser lotteryUser) throws ServiceException {
        return lotteryUserService.addLotteryUser(lotteryUser);
    }


}
