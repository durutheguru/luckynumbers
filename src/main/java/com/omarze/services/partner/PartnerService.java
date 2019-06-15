package com.omarze.services.partner;


import com.omarze.entities.Partner;
import com.omarze.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * created by julian
 */
@Service
public class PartnerService {

    private Components components;


    @Autowired
    public PartnerService(Components components) {
        this.components = components;
    }


    public Partner addPartner(Partner partner) throws ServiceException {
        return new AddPartnerHandler(partner, components).run();
    }


}
