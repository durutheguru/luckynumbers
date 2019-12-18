package com.omarze.services.backoffice;


import com.omarze.api.dto.BackOfficeUserDTO;
import com.omarze.entities.BackOfficeUser;
import com.omarze.exception.ServiceException;

/**
 * created by julian
 */
public interface BackOfficeUserService {


    long userCount();


    boolean backOfficeUserExists();


    BackOfficeUser saveBackOfficeUser(BackOfficeUserDTO userDTO) throws ServiceException;


}
