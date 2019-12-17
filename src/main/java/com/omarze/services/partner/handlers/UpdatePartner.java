package com.omarze.services.partner.handlers;


import com.omarze.entities.Partner;
import com.omarze.exception.InvalidUpdateException;
import com.omarze.exception.ServiceException;
import com.omarze.persistence.PartnerRepository;
import com.omarze.services.Command;

/**
 * created by julian
 */
public class UpdatePartner implements Command<Partner> {


    private final Partner partner;


    private final PartnerRepository partnerRepository;


    public UpdatePartner(Partner partner, PartnerRepository partnerRepository) {
        this.partner = partner;
        this.partnerRepository = partnerRepository;
    }


    @Override
    public Partner execute() throws ServiceException {
        isValidUpdate(partner);
        return partnerRepository.save(partner);
    }


    private boolean isValidUpdate(Partner partner) throws InvalidUpdateException {
        if (partner.getId() == null) {
            throw new InvalidUpdateException("Cannot find existing partner. No ID was supplied");
        }

        return true;
    }


}
