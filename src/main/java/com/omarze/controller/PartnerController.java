package com.omarze.controller;


import com.omarze.entities.Partner;
import com.omarze.exception.ApiException;
import com.omarze.exception.ServiceException;
import com.omarze.services.partner.PartnerComponents;

import com.omarze.services.partner.handlers.AddPartner;
import com.omarze.services.partner.handlers.GetPartner;
import com.omarze.services.partner.handlers.GetPartners;
import com.omarze.services.partner.handlers.UpdatePartner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

/**
 * created by julian
 */
@RestController
@RequestMapping("/partner")
public class PartnerController {


    private PartnerComponents partnerComponents;


    @Autowired
    public PartnerController(PartnerComponents partnerComponents) {
        this.partnerComponents = partnerComponents;
    }


    @PostMapping
    public Partner savePartner(Partner partner) throws ApiException {
        try {
            return new AddPartner(partner).runWith(partnerComponents);
        }
        catch (ServiceException e) {
            throw new ApiException(e);
        }
    }


    @PutMapping
    public Partner updatePartner(Partner partner) throws ApiException {
        try {
            return new UpdatePartner(partner).runWith(partnerComponents);
        }
        catch (ServiceException e) {
            throw new ApiException(e);
        }
    }


    @GetMapping
    public Page<Partner> getPartners(
            @RequestParam("offset") Integer offset, @RequestParam("limit") Integer limit
    ) throws ApiException {
        try {
            return new GetPartners(PageRequest.of(offset, limit, new Sort(Sort.Direction.DESC, "id"))).runWith(partnerComponents);
        }
        catch (ServiceException e) {
            throw new ApiException(e);
        }
    }


    @GetMapping("/{id}")
    public Partner getPartner(@PathVariable Long id) throws ApiException {
        try {
            return new GetPartner(id).runWith(partnerComponents);
        }
        catch (ServiceException e) {
            throw new ApiException(e);
        }
    }


}
