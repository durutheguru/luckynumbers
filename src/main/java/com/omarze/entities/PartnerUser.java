package com.omarze.entities;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Collections;
import java.util.List;

/**
 * created by julian
 */
@Data
@Entity
public class PartnerUser extends ApplicationUser {


    public static final String ROLE_ID = "PARTNER_USER";


    @ManyToOne
    @JoinColumn(nullable = false)
    private Partner partner;


    @Override
    protected List<String> roles() {
        return Collections.singletonList(ROLE_ID);
    }


}

