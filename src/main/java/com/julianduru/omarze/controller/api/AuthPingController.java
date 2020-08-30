package com.julianduru.omarze.controller.api;


import com.julianduru.Constants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by julian
 */
@RestController
@RequestMapping(Constants.API_BASE + "/auth")
public class AuthPingController {


    @GetMapping
    public void ping() { }


}
