package com.julianduru.omarze.controller;


import com.julianduru.omarze.data.lotteryuser.LotteryUserDataProvider;
import com.julianduru.util.JSONUtil;
import com.julianduru.Constants;
import com.julianduru.omarze.api.dto.LotteryUserCampaignDTO;
import com.julianduru.omarze.data.lotteryuser.LotteryUserCampaignDataService;
import com.julianduru.omarze.entities.BackOfficeUser;
import com.julianduru.omarze.entities.LotteryUser;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * created by julian
 */
@WithMockUser(username = BaseControllerTest.TEST_USER, authorities = {LotteryUser.ROLE_ID, BackOfficeUser.ROLE_ID})
public class LotteryUserCampaignControllerTest extends BaseControllerTest {

    @Autowired
    private LotteryUserDataProvider lotteryUserDataProvider;

    @Autowired
    private LotteryUserCampaignDataService userCampaignDataService;



    @Test
    public void testAddingNewLotteryUserCampaign() throws Exception {
        LotteryUser user = new LotteryUser();
        user.setUsername(BaseControllerTest.TEST_USER);
        lotteryUserDataProvider.saveLotteryUser(user);

        LotteryUserCampaignDTO userCampaignDTO = userCampaignDataService.newUserCampaignDTO();

        mockMvc.perform(
                post(Constants.API_BASE + "/user_campaign")
                .content(JSONUtil.asJsonString(userCampaignDTO))
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful());
    }


}

