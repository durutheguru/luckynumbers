package com.omarze.controller;


import com.omarze.dto.PartnerDTO;
import com.omarze.entities.Partner;
import com.omarze.exception.ApiException;
import com.omarze.exception.ServiceException;
import com.omarze.services.partner.PartnerService;
import com.omarze.util.annotation.DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by julian
 */
@RestController
@RequestMapping("/api/v1/partner")
public class PartnerController {


    private PartnerService partnerService;


    @Autowired
    public PartnerController(PartnerService partnerService) {
        this.partnerService = partnerService;
    }


    @PostMapping
    public Partner savePartner(@DTO(PartnerDTO.class) Partner partner) throws ApiException {
        try {
            return partnerService.addPartner(partner);
        }
        catch (ServiceException e) {
            throw new ApiException(e);
        }
    }



}
