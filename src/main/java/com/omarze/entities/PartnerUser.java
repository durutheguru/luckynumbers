package com.omarze.entities;


import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * created by julian
 */
@Entity
public class PartnerUser extends User {


    @ManyToOne
    @JoinColumn(nullable = false)
    private Partner partner;


    public Partner getPartner() {
        return partner;
    }

    public PartnerUser setPartner(Partner partner) {
        this.partner = partner;
        return this;
    }


}
