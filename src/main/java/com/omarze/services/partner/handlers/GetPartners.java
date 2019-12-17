package com.omarze.services.partner.handlers;


import com.omarze.entities.Partner;
import com.omarze.exception.ServiceException;
import com.omarze.persistence.PartnerRepository;
import com.omarze.services.Command;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * created by julian
 */
public class GetPartners implements Command<Page<Partner>> {

    private final PageRequest pageRequest;

    private final PartnerRepository partnerRepository;


    public GetPartners(PageRequest pageRequest, PartnerRepository partnerRepository) {
        this.pageRequest = pageRequest;
        this.partnerRepository = partnerRepository;
    }


    @Override
    public Page<Partner> execute() throws ServiceException {
        return partnerRepository.findAll(pageRequest);
    }


}
