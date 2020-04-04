package com.omarze.api.resource;


import com.omarze.api.dto.BackOfficeUserDTO;
import com.omarze.controller.api.BackOfficeUserApiController;
import com.omarze.entities.BackOfficeUser;
import org.springframework.data.domain.Page;

/**
 * created by julian
 */
public class BackOfficeUserPageResource extends ResourcePage<BackOfficeUser, BackOfficeUserDTO, BackOfficeUserApiController> {


    public BackOfficeUserPageResource(Page<BackOfficeUser> users) {
        super(users, BackOfficeUserDTO.class, BackOfficeUserApiController.class);
    }


}
