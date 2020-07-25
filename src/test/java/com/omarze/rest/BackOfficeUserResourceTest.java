package com.omarze.rest;


import com.julianduru.util.JSONUtil;
import com.omarze.controller.BaseControllerTest;
import com.omarze.data.backoffice.BackOfficeUserDataProvider;
import com.omarze.entities.BackOfficeUser;
import com.omarze.persistence.BackOfficeUserRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * created by julian
 */
//@Transactional
//@Rollback
@Sql(scripts = {"/db/scripts/back_office/init.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = {"/db/scripts/back_office/delete.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@WithMockUser(username = BaseControllerTest.TEST_USER, authorities = {BackOfficeUser.ROLE_ID})
public class BackOfficeUserResourceTest extends BaseControllerTest {


    @Autowired
    BackOfficeUserDataProvider userDataProvider;


    @Autowired
    BackOfficeUserRepository userRepository;



    @Test
    public void testLoadingBackOfficeUsers() throws Exception {
        mockMvc.perform(
            get(API_BASE_PATH + BackOfficeUserRepository.PATH + "?projection=userDetails")
        ).andDo(print())
            .andExpect(status().is2xxSuccessful())
            .andExpect(jsonPath("$._embedded.backOfficeUsers", hasSize(3)));
    }


    @Test
    public void testAddingBackOfficeUser() throws Exception {
        BackOfficeUser user = userDataProvider.newEntity();

        mockMvc.perform(
            post(API_BASE_PATH + BackOfficeUserRepository.PATH)
            .contentType(MediaType.APPLICATION_JSON)
            .content(JSONUtil.asJsonString(user))
        ).andDo(print())
            .andExpect(status().is2xxSuccessful());
    }


    @Test
    public void testAddingBackOfficeUserWhenUsernameAlreadyExists() throws Exception {
        BackOfficeUser existingUser = userRepository.findFirstBy().get();

        BackOfficeUser user = userDataProvider.newEntity();
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
        mockMvc.perform(
            get(API_BASE_PATH + BackOfficeUserRepository.PATH +
                "/search/searchUsers?projection=userDetails&name=superbackoffice2&username=superbackoffice2")
        ).andDo(print())
            .andExpect(status().is2xxSuccessful())
            .andExpect(jsonPath("$._embedded.backOfficeUsers", hasSize(1)));
    }


}

