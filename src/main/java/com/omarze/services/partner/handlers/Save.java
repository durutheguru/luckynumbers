package com.omarze.services.partner.handlers;


import com.julianduru.util.MapperUtil;
import com.omarze.api.dto.PartnerDTO;
import com.omarze.entities.Partner;
import com.omarze.exception.DuplicatePartnerDescriptionException;
import com.omarze.exception.PartnerAlreadyExistsException;
import com.omarze.exception.ServiceException;
import com.omarze.persistence.PartnerRepository;
import com.omarze.services.CommandBase;
import lombok.Builder;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.Optional;

/**
 * created by julian
 */
public class Save extends CommandBase<Partner> {


    @NotNull(message = "Partner Object is required")
    protected final PartnerDTO partnerDTO;


    @NotNull
    protected final PartnerRepository partnerRepository;


    @Builder
    public Save(PartnerDTO partnerDTO, PartnerRepository partnerRepository) {
        this.partnerDTO = partnerDTO;
        this.partnerRepository = partnerRepository;
    }


    @Override
    protected void validate() throws ServiceException {
        checkPartnerExists();
    }


    @Override
    protected Partner execute_() throws ServiceException {
        Partner partner = MapperUtil.map(partnerDTO, Partner.class);

        partner.setTimeAdded(ZonedDateTime.now());

        return partnerRepository.save(partner);
    }


    private void checkPartnerExists() throws ServiceException {
        Optional<Partner> existingPartner = partnerRepository.findByName(partnerDTO.getName());
        if (existingPartner.isPresent()) {
            throw new PartnerAlreadyExistsException(partnerDTO);
        }

        existingPartner = partnerRepository.findByDescription(partnerDTO.getDescription());
        if (existingPartner.isPresent()) {
            throw new DuplicatePartnerDescriptionException();
        }
    }


}



