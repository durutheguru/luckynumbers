package com.omarze.exception;


/**
 * created by julian
 */
public class DuplicatePartnerDescriptionException extends PartnerProcessingException {

    public final static Integer CODE = 10000013;

    public DuplicatePartnerDescriptionException() {
        super("Partner description already exists");
    }

    @Override
    public Integer generateCode() {
        return CODE;
    }

}
