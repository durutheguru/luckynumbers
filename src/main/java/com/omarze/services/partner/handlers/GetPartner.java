package com.omarze.services.partner.handlers;


import com.omarze.entities.Partner;
import com.omarze.exception.ServiceException;
import com.omarze.services.ServiceHandler;
import com.omarze.services.partner.PartnerComponents;

/**
 * created by julian
 */
public class GetPartner implements ServiceHandler<Partner, PartnerComponents> {

    private final Long id;


    public GetPartner(Long id) {
        this.id = id;
    }


    @Override
    public Partner runWith(PartnerComponents components) throws ServiceException {
        return components.partnerRepository.getOne(id);
    }



}
