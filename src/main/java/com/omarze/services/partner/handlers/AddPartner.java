package com.omarze.services.partner.handlers;


import com.omarze.entities.Partner;
import com.omarze.exception.ServiceException;
import com.omarze.services.ServiceHandler;
import com.omarze.services.partner.PartnerComponents;

/**
 * created by julian
 */
public class AddPartner implements ServiceHandler<Partner, PartnerComponents> {

    private final Partner partner;


    public AddPartner(Partner partner) {
        this.partner = partner;
    }


    @Override
    public Partner runWith(PartnerComponents components) throws ServiceException {
        return components.partnerRepository.save(partner);
    }


}
