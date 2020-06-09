package com.omarze.persistence.handlers;


import com.omarze.entities.Partner;
import com.omarze.exception.ServiceException;
import com.omarze.persistence.validators.PartnerEventValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

/**
 * created by julian
 */
@Component
@RepositoryEventHandler
@RequiredArgsConstructor
public class PartnerHandler {


    final PartnerEventValidator partnerEventValidator;


    @HandleBeforeCreate
    public void handleSavePartner(Partner partner) throws ServiceException {
        partnerEventValidator.validateNewPartner(partner);
    }


}

