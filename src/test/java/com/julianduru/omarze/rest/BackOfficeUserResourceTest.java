package com.julianduru.omarze.rest;


import com.julianduru.util.JSONUtil;
import com.julianduru.omarze.controller.BaseControllerTest;
import com.julianduru.omarze.data.backoffice.BackOfficeUserDataProvider;
import com.julianduru.omarze.entities.BackOfficeUser;
import com.julianduru.omarze.persistence.BackOfficeUserRepository;
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
public class BackOfficeUserResourceTest extends BaseControllerTest {


    @Autowired
    BackOfficeUserDataProvider userDataProvider;


    @Test
    public void testLoadingBackOfficeUsers() throws Exception {
        userDataProvider.save(3);

        mockMvc.perform(
            get(API_BASE_PATH + BackOfficeUserRepository.PATH + "?projection=userDetails")
        ).andDo(print())
            .andExpect(status().is2xxSuccessful())
            .andExpect(jsonPath("$._embedded.backOfficeUsers", hasSize(greaterThanOrEqualTo(3))));
    }


    @Test
    public void testAddingBackOfficeUser() throws Exception {
        BackOfficeUser user = userDataProvider.provide();

        mockMvc.perform(
            post(API_BASE_PATH + BackOfficeUserRepository.PATH)
            .contentType(MediaType.APPLICATION_JSON)
            .content(JSONUtil.asJsonString(user))
        ).andDo(print())
            .andExpect(status().is2xxSuccessful());
    }


    @Test
    public void testAddingBackOfficeUserWhenUsernameAlreadyExists() throws Exception {
        BackOfficeUser existingUser = userDataProvider.getOrSave();

        BackOfficeUser user = userDataProvider.provide();
        user.setUsername(existingUser.getUsername());

        mockMvc.perform(
            post(API_BASE_PATH + BackOfficeUserRepository.PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSONUtil.asJsonString(user))
        ).andDo(print())
            .andExpect(status().is5xxServerError());
    }


    @Test
    public void testSearchingBackOfficeUsers() throws Exception {
        BackOfficeUser sample1 = new BackOfficeUser();
        sample1.setName("superbackoffice2");

        BackOfficeUser sample2 = new BackOfficeUser();
        sample2.setUsername("superbackoffice2");

        userDataProvider.save(sample1, sample2);

        mockMvc.perform(
            get(API_BASE_PATH + BackOfficeUserRepository.PATH +
                "/search/searchUsers?projection=userDetails&name=superbackoffice2&username=superbackoffice2")
        ).andDo(print())
            .andExpect(status().is2xxSuccessful())
            .andExpect(jsonPath("$._embedded.backOfficeUsers", hasSize(2)));
    }


}

