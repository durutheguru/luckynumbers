package com.julianduru.omarze.persistence.handlers;


import com.julianduru.omarze.entities.BackOfficeUser;
import com.julianduru.omarze.exception.ServiceException;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

/**
 * created by julian
 */
@Component
@RepositoryEventHandler
public class BackOfficeUserHandler extends AbstractApplicationUserHandler {



    @HandleBeforeCreate
    public void handleBeforeCreate(BackOfficeUser user) throws ServiceException {
        prepareApplicationUser(user);
    }


}


