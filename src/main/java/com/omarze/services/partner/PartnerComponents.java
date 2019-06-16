package com.omarze.services.partner;


import com.omarze.persistence.PartnerRepository;
import com.omarze.services.Components;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * created by julian
 */
@Component
public class PartnerComponents extends Components {


    @Autowired
    public PartnerRepository partnerRepository;



}
