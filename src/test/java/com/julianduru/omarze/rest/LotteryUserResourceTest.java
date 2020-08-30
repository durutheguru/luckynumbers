package com.julianduru.omarze.rest;


import com.julianduru.util.JSONUtil;
import com.julianduru.omarze.controller.BaseControllerTest;
import com.julianduru.omarze.data.lotteryuser.LotteryUserDataProvider;
import com.julianduru.omarze.entities.BackOfficeUser;
import com.julianduru.omarze.entities.LotteryUser;
import com.julianduru.omarze.persistence.LotteryUserRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * created by julian
 */
@WithMockUser(username = BaseControllerTest.TEST_USER, authorities = {BackOfficeUser.ROLE_ID})
public class LotteryUserResourceTest extends BaseControllerTest {


    @Autowired
    LotteryUserRepository userRepository;


    @Autowired
    LotteryUserDataProvider userDataProvider;


    @Test
    public void testLoadingLotteryUsers() throws Exception {
        userDataProvider.save(2);

        mockMvc.perform(
            get(API_BASE_PATH + LotteryUserRepository.PATH)
        ).andDo(print())
            .andExpect(status().is2xxSuccessful())
            .andExpect(jsonPath("$._embedded.lotteryUsers", hasSize(greaterThanOrEqualTo(1))));
    }


    @Test
    public void testAddingLotteryUser() throws Exception {
        LotteryUser lotteryUser = userDataProvider.provide();

        mockMvc.perform(
            post(API_BASE_PATH + LotteryUserRepository.PATH)
            .contentType(MediaType.APPLICATION_JSON)
            .content(JSONUtil.asJsonString(lotteryUser))
        ).andDo(print())
            .andExpect(status().is2xxSuccessful());
    }


    @Test
    public void testAddingLotteryUserWhenUsernameAlreadyExists() throws Exception {
        LotteryUser lotteryUser = userDataProvider.getOrSave();

        LotteryUser newUser = userDataProvider.provide();
        newUser.setUsername(lotteryUser.getUsername());

        mockMvc.perform(
            post(API_BASE_PATH + LotteryUserRepository.PATH)
            .contentType(MediaType.APPLICATION_JSON)
            .content(JSONUtil.asJsonString(newUser))
        ).andDo(print())
            .andExpect(status().is5xxServerError());
    }


    @Test
    public void testSearchingLotteryUsers() throws Exception {
        LotteryUser existingUser = userDataProvider.getOrSave();

        mockMvc.perform(
            get(API_BASE_PATH + LotteryUserRepository.PATH +
                String.format(
                    "/search/searchUsers?projection=userDetails&name=%s&username=%s",
                    existingUser.getUsername(), existingUser.getUsername()
                )
            )
        ).andDo(print())
            .andExpect(status().is2xxSuccessful())
            .andExpect(jsonPath("$._embedded.lotteryUsers", hasSize(1)));
    }


}
