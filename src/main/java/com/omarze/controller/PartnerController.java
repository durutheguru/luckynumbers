package com.omarze.controller;


import com.omarze.entities.Partner;
import com.omarze.exception.ServiceException;
import com.omarze.services.partner.PartnerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * created by julian
 */
@RestController
@RequestMapping("/api/v1/partners")
public class PartnerController {


    private PartnerService partnerService;


    @Autowired
    public PartnerController(PartnerService partnerService) {
        this.partnerService = partnerService;
    }


    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Partner savePartner(Partner partner) throws ServiceException {
        return partnerService.savePartner(partner);
    }


    @PutMapping
    public Partner updatePartner(Partner partner) throws ServiceException {
        return partnerService.updatePartner(partner);
    }


    @GetMapping
    public Page<Partner> getPartners(
            @RequestParam(name = "offset", defaultValue = "0") Integer offset, @RequestParam(name = "limit", defaultValue = "10") Integer limit
    ) throws ServiceException {
        return partnerService.getPartners(offset, limit);
    }


    @GetMapping("/{id}")
    public Partner getPartner(@PathVariable Long id) throws ServiceException {
        return partnerService.getPartner(id);
    }


}
