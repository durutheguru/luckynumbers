package com.omarze.services.lotteryuser;


import com.omarze.api.dto.LotteryUserDTO;
import com.omarze.entities.LotteryUser;
import com.omarze.exception.ServiceException;

/**
 * created by julian
 */
public interface LotteryUserService {


    LotteryUser addLotteryUser(LotteryUserDTO lotteryUser) throws ServiceException;


}


