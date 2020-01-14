package com.omarze.controller.api;


import com.omarze.Constants;
import com.omarze.api.dto.BackOfficeUserDTO;
import com.omarze.entities.BackOfficeUser;
import com.omarze.exception.ServiceException;
import com.omarze.security.annotation.IsBackOfficeUser;
import com.omarze.services.backoffice.BackOfficeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * created by julian
 */
@RestController
@RequestMapping(BackOfficeUserController.PATH)
public class BackOfficeUserController extends ApiBaseController {


    public final static String PATH = Constants.API_BASE + "/back_office_user";


    private BackOfficeUserService backOfficeUserService;


    @Autowired
    public BackOfficeUserController setBackOfficeUserService(BackOfficeUserService backOfficeUserService) {
        this.backOfficeUserService = backOfficeUserService;
        return this;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @IsBackOfficeUser
    public BackOfficeUserDTO saveNewUser(
        @RequestBody BackOfficeUserDTO userDTO
    ) throws ServiceException {
        BackOfficeUser newUser = backOfficeUserService.saveBackOfficeUser(userDTO);

        return map(newUser, BackOfficeUserDTO.class);
    }


}
