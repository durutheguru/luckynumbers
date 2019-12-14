package com.omarze.services.partner.handlers;


import com.omarze.api.dto.PartnerDTO;
import com.omarze.entities.Partner;
import com.omarze.exception.*;
import com.omarze.persistence.PartnerRepository;
import com.omarze.services.Command;
import com.omarze.services.CommandBase;
import com.omarze.util.MapperUtil;
import com.omarze.util.ValidatorUtil;
import lombok.Builder;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * created by julian
 */
public class Save extends CommandBase<Partner> {


    @NotNull(message = "Partner Object is required")
    private final PartnerDTO partnerDto;


    @NotNull
    private final PartnerRepository partnerRepository;


    @Builder
    public Save(PartnerDTO partnerDto, PartnerRepository partnerRepository) {
        this.partnerDto = partnerDto;
        this.partnerRepository = partnerRepository;
    }


    protected void validate() throws ServiceException {
        partnerExists();
    }


    @Override
    protected Partner execute_() throws ServiceException {
        Partner partner = MapperUtil.map(partnerDto, Partner.class);

        partner.setTimeAdded(LocalDateTime.now());

        return partnerRepository.save(partner);
    }


    private void partnerExists() throws PartnerProcessingException {
        Optional<Partner> existingPartner = partnerRepository.findByName(partnerDto.getName());
        if (existingPartner.isPresent()) {
            throw new PartnerAlreadyExistsException(partnerDto);
        }

        existingPartner = partnerRepository.findByDescription(partnerDto.getDescription());
        if (existingPartner.isPresent()) {
            throw new DuplicatePartnerDescriptionException();
        }
    }


}
