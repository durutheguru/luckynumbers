package com.omarze.services.partner;


import com.omarze.entities.Partner;
import com.omarze.services.ServiceHandler;

/**
 * created by julian
 */
public class AddPartnerHandler implements ServiceHandler<Partner> {

    private final Partner partner;

    private final Components components;


    public AddPartnerHandler(Partner partner, Components components) {
        this.partner = partner;
        this.components = components;
    }


    @Override
    public Partner run() {
        return components.partnerRepository.save(partner);
    }


}
