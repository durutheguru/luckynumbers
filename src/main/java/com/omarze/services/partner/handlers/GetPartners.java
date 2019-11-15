package com.omarze.services.partner.handlers;


import com.omarze.entities.Partner;
import com.omarze.exception.ServiceException;
import com.omarze.persistence.PartnerRepository;
import com.omarze.services.ServiceHandler;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * created by julian
 */
public class GetPartners implements ServiceHandler<Page<Partner>> {

    private final PageRequest pageRequest;

    private final PartnerRepository partnerRepository;


    public GetPartners(PageRequest pageRequest, PartnerRepository partnerRepository) {
        this.pageRequest = pageRequest;
        this.partnerRepository = partnerRepository;
    }


    @Override
    public Page<Partner> run() throws ServiceException {
        return partnerRepository.findAll(pageRequest);
    }


}
