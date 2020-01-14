package com.omarze.controller.api;


import com.omarze.Constants;
import com.omarze.api.dto.LotteryUserDTO;
import com.omarze.entities.LotteryUser;
import com.omarze.exception.ServiceException;
import com.omarze.services.lotteryuser.LotteryUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * created by julian
 */
@RestController
@RequestMapping(LotteryUserController.PATH)
public class LotteryUserController extends ApiBaseController {


    public final static String PATH = Constants.API_BASE + "/lottery_user";


    private LotteryUserService lotteryUserService;


    @Autowired
    public LotteryUserController setLotteryUserService(LotteryUserService lotteryUserService) {
        this.lotteryUserService = lotteryUserService;
        return this;
    }


    @PostMapping("/sign_up")
    @ResponseStatus(HttpStatus.CREATED)
    public LotteryUserDTO addLotteryUser(
            @RequestBody @Valid LotteryUserDTO lotteryUserDTO
    ) throws ServiceException {
        LotteryUser lotteryUser = lotteryUserService.addLotteryUser(lotteryUserDTO);

        return map(lotteryUser, LotteryUserDTO.class);
    }


}


