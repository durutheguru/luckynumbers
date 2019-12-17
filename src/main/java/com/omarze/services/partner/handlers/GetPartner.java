package com.omarze.services.partner.handlers;


import com.omarze.entities.Partner;
import com.omarze.exception.ServiceException;
import com.omarze.persistence.PartnerRepository;
import com.omarze.services.Command;

/**
 * created by julian
 */
public class GetPartner implements Command<Partner> {

    private final Long id;

    private final PartnerRepository partnerRepository;


    public GetPartner(Long id, PartnerRepository partnerRepository) {
        this.id = id;
        this.partnerRepository = partnerRepository;
    }


    @Override
    public Partner execute() throws ServiceException {
        return partnerRepository.getOne(id);
    }



}
