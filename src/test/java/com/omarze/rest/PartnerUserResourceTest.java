package com.omarze.rest;


import com.omarze.controller.BaseControllerTest;
import com.omarze.data.partner.PartnerUserDataProvider;
import com.omarze.entities.BackOfficeUser;
import com.omarze.entities.Partner;
import com.omarze.entities.PartnerUser;
import com.omarze.persistence.PartnerRepository;
import com.omarze.persistence.PartnerUserRepository;
import com.omarze.util.JSONUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

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
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Sql({"/db/scripts/partner/init.sql"})
@WithMockUser(username = BaseControllerTest.TEST_USER, authorities = {BackOfficeUser.ROLE_ID})
public class PartnerUserResourceTest extends BaseControllerTest {


    @Autowired
    PartnerUserRepository userRepository;


    @Autowired
    PartnerRepository partnerRepository;


    @Autowired
    PartnerUserDataProvider userDataProvider;



    @Test
    public void testLoadingPartnerUsers() throws Exception {
        mockMvc.perform(
            get(API_BASE_PATH + PartnerUserRepository.PATH + "?projection=userDetails")
        ).andDo(print())
            .andExpect(status().is2xxSuccessful())
            .andExpect(jsonPath("$._embedded.partnerUsers", hasSize(greaterThanOrEqualTo(2))));
    }


    @Test
    @Transactional
    public void testAddingPartnerUser() throws Exception {
        Partner partner = partnerRepository.findFirstBy().get();

        PartnerUser partnerUser = userDataProvider.newEntity(partner);

        mockMvc.perform(
            post(API_BASE_PATH + PartnerUserRepository.PATH)
            .contentType(MediaType.APPLICATION_JSON)
            .content(JSONUtil.asJsonString(partnerUser))
        ).andDo(print())
            .andExpect(status().is2xxSuccessful());
    }


    @Test
    @Transactional
    public void testAddingPartnerUserWhenUsernameAlreadyExists() throws Exception {
        PartnerUser existingUser = userRepository.findFirstBy().get();

        PartnerUser newUser = userDataProvider.newEntity(existingUser.getPartner());
        newUser.setUsername(existingUser.getUsername());

        mockMvc.perform(
            post(API_BASE_PATH + PartnerUserRepository.PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSONUtil.asJsonString(newUser))
        ).andDo(print())
            .andExpect(status().is5xxServerError());
    }


    @Test
    public void testSearchingPartnerUsers() throws Exception {
        PartnerUser existingUser = userRepository.findFirstBy().get();

        mockMvc.perform(
            get(API_BASE_PATH + PartnerUserRepository.PATH +
                String.format(
                    "/search/searchUsers?projection=userDetails&name=%s&username=%s",
                    existingUser.getUsername(), existingUser.getUsername()
                )
            )
        ).andDo(print())
            .andExpect(status().is2xxSuccessful())
            .andExpect(jsonPath("$._embedded.partnerUsers", hasSize(1)));
    }


}

