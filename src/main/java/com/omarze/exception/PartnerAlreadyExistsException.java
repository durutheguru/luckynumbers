package com.omarze.exception;


import com.omarze.api.dto.PartnerDTO;

/**
 * created by julian
 */
public class PartnerAlreadyExistsException extends PartnerProcessingException {

    public final static Integer CODE = 1000003;


    public PartnerAlreadyExistsException(PartnerDTO partnerDto) {
        super(String.format("Partner Already exists '%s'", partnerDto.getName()));
    }


    @Override
    public Integer generateCode() {
        return CODE;
    }

}
