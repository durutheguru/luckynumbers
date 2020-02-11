package com.omarze.api.resource;


import com.omarze.api.dto.BackOfficeUserDTO;
import com.omarze.controller.api.BackOfficeUserApiController;
import com.omarze.entities.BackOfficeUser;
import com.omarze.exception.ServiceException;

/**
 * created by julian
 */
public class BackOfficeUserResource extends Resource<BackOfficeUser, BackOfficeUserDTO, BackOfficeUserApiController> {


    public BackOfficeUserResource(BackOfficeUser entity) throws ServiceException {
        super(entity, BackOfficeUserDTO.class, BackOfficeUserApiController.class);
    }


}

