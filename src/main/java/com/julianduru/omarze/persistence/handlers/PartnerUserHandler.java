package com.julianduru.omarze.persistence.handlers;


import com.julianduru.omarze.entities.PartnerUser;
import com.julianduru.omarze.exception.ServiceException;
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
public class PartnerUserHandler extends AbstractApplicationUserHandler {


    @HandleBeforeCreate
    public void handleBeforeCreate(PartnerUser user) throws ServiceException {
        prepareApplicationUser(user);
    }


}

