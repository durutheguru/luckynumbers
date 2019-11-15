package com.omarze.services.partner;


import com.omarze.entities.Partner;
import com.omarze.exception.ServiceException;
import com.omarze.persistence.PartnerRepository;
import com.omarze.services.partner.handlers.AddPartner;
import com.omarze.services.partner.handlers.GetPartner;
import com.omarze.services.partner.handlers.GetPartners;
import com.omarze.services.partner.handlers.UpdatePartner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * created by julian
 */
@Service
public class PartnerService {


    public PartnerRepository partnerRepository;


    @Autowired
    public PartnerService setPartnerRepository(PartnerRepository partnerRepository) {
        this.partnerRepository = partnerRepository;
        return this;
    }


    public Partner savePartner(Partner partner) throws ServiceException {
        return new AddPartner(partner, partnerRepository).run();
    }


    public Partner updatePartner(Partner partner) throws ServiceException {
        return new UpdatePartner(partner, partnerRepository).run();
    }


    public Page<Partner> getPartners(Integer offset, Integer limit) throws ServiceException {
        return new GetPartners(PageRequest.of(offset, limit, new Sort(Sort.Direction.DESC, "id")), partnerRepository).run();
    }


    public Partner getPartner(@PathVariable Long id) throws ServiceException {
        return new GetPartner(id, partnerRepository).run();
    }



}
