package com.omarze.controller;


import com.omarze.Constants;
import com.omarze.api.dto.PartnerDTO;
import com.omarze.entities.Partner;
import com.omarze.exception.ServiceException;
import com.omarze.security.annotation.IsBackOfficeUser;
import com.omarze.security.annotation.IsLotteryUser;
import com.omarze.services.partner.PartnerService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * created by julian
 */
@RestController
@RequestMapping(PartnerController.PATH)
public class PartnerController {


    public final static String PATH = Constants.API_BASE + "/partner";


    private PartnerService partnerService;


    public PartnerController(PartnerService partnerService) {
        this.partnerService = partnerService;
    }


    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    @IsBackOfficeUser
    public Partner savePartner(
            @Valid @RequestBody PartnerDTO partnerDTO
    ) throws ServiceException {
        return partnerService.savePartner(partnerDTO);
    }


    @PutMapping
    @IsBackOfficeUser
    public Partner updatePartner(
            @RequestBody Partner partner
    ) throws ServiceException {
        return partnerService.updatePartner(partner);
    }


    @GetMapping
    @IsLotteryUser
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
