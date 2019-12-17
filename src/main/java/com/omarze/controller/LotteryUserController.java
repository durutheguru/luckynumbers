package com.omarze.controller;


import com.omarze.Constants;
import com.omarze.api.dto.LotteryUserDTO;
import com.omarze.entities.LotteryUser;
import com.omarze.exception.ServiceException;
import com.omarze.services.lotteryuser.LotteryUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * created by julian
 */
@RestController
@RequestMapping(Constants.API_BASE + "/lottery_user")
public class LotteryUserController {


    private LotteryUserService lotteryUserService;


    @Autowired
    public LotteryUserController setLotteryUserService(LotteryUserService lotteryUserService) {
        this.lotteryUserService = lotteryUserService;
        return this;
    }


    @PostMapping("/sign_up")
    @ResponseStatus(HttpStatus.CREATED)
    public LotteryUser addLotteryUser(
            @RequestBody LotteryUser lotteryUser
    ) throws ServiceException {
        return lotteryUserService.addLotteryUser(lotteryUser);
    }


}
