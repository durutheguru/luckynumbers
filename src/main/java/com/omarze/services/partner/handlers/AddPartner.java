package com.omarze.services.partner.handlers;


import com.omarze.entities.Partner;
import com.omarze.exception.*;
import com.omarze.persistence.PartnerRepository;
import com.omarze.services.ServiceHandler;
import com.omarze.util.ValidatorUtil;

import java.util.Optional;

/**
 * created by julian
 */
public class AddPartner implements ServiceHandler<Partner> {

    private final Partner partner;

    private final PartnerRepository partnerRepository;


    public AddPartner(Partner partner, PartnerRepository partnerRepository) {
        this.partner = partner;
        this.partnerRepository = partnerRepository;
    }


    @Override
    public Partner run() throws ServiceException {

        if (!validNewPartner()) {
            throw new InvalidObjectException("Unable to create partner");
        }

        return partnerRepository.save(partner);
    }


    private boolean validNewPartner() throws InvalidObjectException, PartnerProcessingException {
        ValidatorUtil.validate(partner);
        return !partnerExists();
    }


    private boolean partnerExists() throws PartnerProcessingException {
        Optional<Partner> existingPartner = partnerRepository.findByName(partner.getName());
        if (existingPartner.isPresent()) {
            throw new PartnerAlreadyExistsException(partner);
        }

        existingPartner = partnerRepository.findByDescription(partner.getDescription());
        if (existingPartner.isPresent()) {
            throw new DuplicatePartnerDescriptionException();
        }

        return false;
    }


}
