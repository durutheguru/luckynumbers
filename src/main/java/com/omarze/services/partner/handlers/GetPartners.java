package com.omarze.services.partner.handlers;


import com.omarze.entities.Partner;
import com.omarze.exception.ServiceException;
import com.omarze.services.ServiceHandler;
import com.omarze.services.partner.PartnerComponents;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * created by julian
 */
public class GetPartners implements ServiceHandler<Page<Partner>, PartnerComponents> {

    private final PageRequest pageRequest;


    public GetPartners(PageRequest pageRequest) {
        this.pageRequest = pageRequest;
    }


    @Override
    public Page<Partner> runWith(PartnerComponents components) throws ServiceException {
        return components.partnerRepository.findAll(pageRequest);
    }


}
