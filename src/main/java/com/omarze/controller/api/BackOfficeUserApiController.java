package com.omarze.controller.api;


import com.omarze.Constants;
import com.omarze.api.dto.BackOfficeUserDTO;
import com.omarze.api.resource.BackOfficeUserPageResource;
import com.omarze.api.resource.BackOfficeUserResource;
import com.omarze.entities.BackOfficeUser;
import com.omarze.exception.ServiceException;
import com.omarze.security.annotation.IsBackOfficeUser;
import com.omarze.services.backoffice.BackOfficeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * created by julian
 */
@RestController
@RequestMapping(BackOfficeUserApiController.PATH)
public class BackOfficeUserApiController extends BaseApiController<BackOfficeUserResource, BackOfficeUserPageResource> {


    public static final String PATH = Constants.API_BASE + "/back_office_user";


    private BackOfficeUserService backOfficeUserService;


    @Autowired
    public BackOfficeUserApiController setBackOfficeUserService(BackOfficeUserService backOfficeUserService) {
        this.backOfficeUserService = backOfficeUserService;
        return this;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @IsBackOfficeUser
    public BackOfficeUserResource saveNewUser(
        @RequestBody BackOfficeUserDTO userDTO
    ) throws ServiceException {
        BackOfficeUser newUser = backOfficeUserService.saveBackOfficeUser(userDTO);
        return new BackOfficeUserResource(newUser);
    }


    @Override
    @GetMapping("/{id}")
    @IsBackOfficeUser
    public BackOfficeUserResource getById(@PathVariable Long id) throws ServiceException {
        BackOfficeUser user = backOfficeUserService.getById(id);
        return new BackOfficeUserResource(user);
    }


    @GetMapping
    @IsBackOfficeUser
    public BackOfficeUserPageResource getPage(
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size
    ) throws ServiceException {
        Page<BackOfficeUser> users = backOfficeUserService.getUsers(page, size);
        return new BackOfficeUserPageResource(users);
    }


}

