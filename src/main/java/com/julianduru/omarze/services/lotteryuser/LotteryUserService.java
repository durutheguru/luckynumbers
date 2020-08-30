package com.julianduru.omarze.services.lotteryuser;


import com.julianduru.omarze.api.dto.LotteryUserDTO;
import com.julianduru.omarze.entities.LotteryUser;
import com.julianduru.omarze.exception.ServiceException;

/**
 * created by julian
 */
public interface LotteryUserService {


    LotteryUser addLotteryUser(LotteryUserDTO lotteryUser) throws ServiceException;


}


