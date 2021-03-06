package com.julianduru.omarze.rest;


import com.google.common.base.Strings;
import com.julianduru.omarze.controller.BaseControllerTest;
import com.julianduru.omarze.controller.api.PartnerController;
import com.julianduru.omarze.data.partner.PartnerDataProvider;
import com.julianduru.omarze.entities.BackOfficeUser;
import com.julianduru.omarze.entities.Partner;
import com.julianduru.omarze.persistence.PartnerRepository;
import com.julianduru.util.JSONUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * created by julian
 */
//@Sql(scripts = {"/db/scripts/partner/init.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//@Sql(scripts = {"/db/scripts/partner/delete.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@WithMockUser(username = BaseControllerTest.TEST_USER, authorities = {BackOfficeUser.ROLE_ID})
public class PartnerResourceTest extends BaseControllerTest {


    @Autowired
    private PartnerDataProvider partnerDataProvider;


    @Autowired
    private PartnerRepository partnerRepository;



    @Test
    public void testSavingPartner() throws Exception {
        Partner partner = partnerDataProvider.provide();

        mockMvc.perform(
            post(API_BASE_PATH + PartnerRepository.PATH)
                .content(JSONUtil.asJsonString(partner))
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print())
            .andExpect(status().is2xxSuccessful());
    }


    @Test
    public void testSavingAlreadyExistingPartnerName() throws Exception {
        Partner partner = partnerDataProvider.getOrSave();

        Partner newPartner = partnerDataProvider.provide();
        newPartner.setName(partner.getName());


        mockMvc.perform(
            post(API_BASE_PATH + PartnerRepository.PATH)
                .content(JSONUtil.asJsonString(newPartner))
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print())
            .andExpect(status().is5xxServerError());
    }


    @Test
    public void testLoadingPartners() throws Exception {
        partnerDataProvider.save(3);

        mockMvc.perform(
            get(API_BASE_PATH + PartnerRepository.PATH)
        ).andDo(print())
            .andExpect(status().is2xxSuccessful());
    }


    @Test
    public void testLoadingPartnersMinimumData() throws Exception {
        partnerDataProvider.save(2);

        mockMvc.perform(
            get(API_BASE_PATH + PartnerRepository.PATH + "/search/findBy?projection=partnerMinDetails&sort=name,asc")
        ).andDo(print())
            .andExpect(status().is2xxSuccessful());
    }


//    @Test
//    public void testUpdatingPartner() throws Exception {
//
//        PartnerDTO partnerDTO = partnerDataService.newPartnerDTO();
//
//        String savedPartnerResponse = mockMvc.perform(
//                post(PartnerController.PATH)
//                .content(JSONUtil.asJsonString(partnerDTO))
//                .contentType(MediaType.APPLICATION_JSON)
//        ).andDo(MockMvcResultHandlers.print())
//                .andExpect(status().is2xxSuccessful())
//                .andReturn().getResponse().getContentAsString();
//
//        mockMvc.perform(
//                put(PartnerController.PATH)
//                .content(new JSONObject((LinkedHashMap)JsonPath.read(savedPartnerResponse, "$")).toString())
//                .contentType(MediaType.APPLICATION_JSON)
//        ).andDo(MockMvcResultHandlers.print())
//                .andExpect(status().isOk());
//    }


    @Test
    public void testSavingPartnerWithLongNameCausesError() throws Exception {
        Partner partner = partnerDataProvider.provide();
        partner.setName(Strings.repeat("Long Arse Name", 100));

        mockMvc.perform(
            post(API_BASE_PATH + PartnerRepository.PATH)
                .content(JSONUtil.asJsonString(partner))
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print())
            .andExpect(status().is4xxClientError());
    }


    @Test
    public void testUploadingPartnerImages() throws Exception {
        Partner partner = partnerDataProvider.save();

        MockMultipartFile file0 = new MockMultipartFile("files", "img0.jpg", MediaType.APPLICATION_OCTET_STREAM_VALUE, new ClassPathResource("img/img0.jpg").getInputStream());
        MockMultipartFile file2 = new MockMultipartFile("files", "img2.jpg", MediaType.APPLICATION_OCTET_STREAM_VALUE, new ClassPathResource("img/img2.jpg").getInputStream());

        mockMvc.perform(
            multipart(PartnerController.PATH + "/" + partner.getId() + "/image")
                .file(file0)
                .file(file2)
        ).andDo(print())
            .andExpect(status().is2xxSuccessful());
    }


}


