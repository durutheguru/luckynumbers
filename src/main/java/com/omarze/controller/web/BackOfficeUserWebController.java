package com.omarze.controller.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * created by julian
 */
@Controller
@RequestMapping(BackOfficeUserWebController.PATH)
public class BackOfficeUserWebController {


    public static final String PATH = "/back_office_user";


    @GetMapping
    public String index() {
        return "back_office_user/index";
    }


    @GetMapping("/users")
    public String users() {
        return "back_office_user/users/index";
    }



}


