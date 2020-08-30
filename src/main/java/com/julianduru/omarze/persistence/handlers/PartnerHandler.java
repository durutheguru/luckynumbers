package com.julianduru.omarze.persistence.handlers;


import com.julianduru.omarze.entities.Partner;
import com.julianduru.omarze.exception.ServiceException;
import com.julianduru.omarze.persistence.validators.PartnerEventValidator;
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

