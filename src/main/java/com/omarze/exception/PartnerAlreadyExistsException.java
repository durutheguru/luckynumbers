package com.omarze.exception;


import com.omarze.api.dto.PartnerDTO;

/**
 * created by julian
 */
public class PartnerAlreadyExistsException extends ServiceException {


    public PartnerAlreadyExistsException(PartnerDTO partnerDto) {
        super(String.format("Partner Already exists '%s'", partnerDto.getName()));
    }


}
