package com.omarze.exception;


import com.omarze.entities.Partner;

/**
 * created by julian
 */
public class PartnerAlreadyExistsException extends PartnerProcessingException {

    public final static Integer CODE = 1000003;


    public PartnerAlreadyExistsException(Partner partner) {
        super(String.format("Partner Already exists '%s'", partner.getName()));
    }


    @Override
    public Integer generateCode() {
        return CODE;
    }

}
