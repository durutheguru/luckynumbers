package com.julianduru.omarze.services.backoffice;


import com.julianduru.omarze.api.dto.BackOfficeUserDTO;
import com.julianduru.omarze.entities.BackOfficeUser;
import com.julianduru.omarze.exception.EntityNotFoundException;
import com.julianduru.omarze.exception.ServiceException;
import org.springframework.data.domain.Page;

/**
 * created by julian
 */
public interface BackOfficeUserService {


    long userCount();


    boolean backOfficeUserExists();


    BackOfficeUser saveBackOfficeUser(BackOfficeUserDTO userDTO) throws ServiceException;


    BackOfficeUser getById(Long id) throws EntityNotFoundException;


    Page<BackOfficeUser> getUsers(int page, int size) throws ServiceException;

}
