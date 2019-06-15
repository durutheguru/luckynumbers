package com.omarze.services.partner;


import com.omarze.persistence.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * created by julian
 */
@Component
public class Components {


    @Autowired
    protected PartnerRepository partnerRepository;



}
