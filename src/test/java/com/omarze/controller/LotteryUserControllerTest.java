package com.omarze.controller;


import com.omarze.api.dto.LotteryUserDTO;
import com.omarze.controller.api.LotteryUserController;
import com.omarze.data.lotteryuser.LotteryUserDataService;
import com.omarze.services.lotteryuser.LotteryUserService;
import com.omarze.util.JSONUtil;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * created by julian
 */
public class LotteryUserControllerTest extends BaseControllerTest {


    @Autowired
    private LotteryUserDataService userDataService;


    @MockBean
    private LotteryUserService lotteryUserService;



    @Test
    public void testLotteryUserSignUp() throws Exception {

        LotteryUserDTO userDTO = userDataService.newLotteryUserDTO();

        Mockito.when(lotteryUserService.addLotteryUser(any())).thenReturn(userDataService.newLotteryUser());

        mockMvc.perform(
            post(LotteryUserController.PATH + "/sign_up")
            .content(JSONUtil.asJsonString(userDTO))
            .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print())
            .andExpect(status().is2xxSuccessful());

    }



}
