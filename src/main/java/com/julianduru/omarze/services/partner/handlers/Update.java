package com.julianduru.omarze.services.partner.handlers;


import com.julianduru.util.MapperUtil;
import com.julianduru.omarze.api.dto.PartnerDTO;
import com.julianduru.omarze.entities.Partner;
import com.julianduru.omarze.exception.InvalidUpdateException;
import com.julianduru.omarze.exception.ServiceException;
import com.julianduru.omarze.persistence.PartnerRepository;
import com.julianduru.omarze.services.CommandBase;
import lombok.Builder;

import javax.validation.constraints.NotNull;

/**
 * created by julian
 */
public class Update extends CommandBase<Partner> {


    @NotNull(message = "Partner Object is required")
    protected final PartnerDTO partnerDTO;


    @NotNull
    protected final PartnerRepository partnerRepository;


    @Builder
    public Update(PartnerDTO partnerDTO, PartnerRepository partnerRepository) {
        this.partnerDTO = partnerDTO;
        this.partnerRepository = partnerRepository;
    }


    @Override
    public Partner execute_() throws ServiceException {
        Partner partner = MapperUtil.map(partnerDTO, Partner.class);
        return partnerRepository.save(partner);
    }


    @Override
    protected void validate() throws ServiceException {
        if (partnerDTO == null || partnerDTO.getId() == null) {
            throw new InvalidUpdateException("Cannot find existing partner. No ID was supplied");
        }
    }


}



