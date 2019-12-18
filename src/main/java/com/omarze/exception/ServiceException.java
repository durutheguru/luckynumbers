package com.omarze.exception;


import com.google.common.base.Strings;
import com.omarze.util.CryptoUtil;
import org.slf4j.LoggerFactory;

/**
 * created by julian
 */
public abstract class ServiceException extends Exception {

    protected Integer code = 1000000;


    public ServiceException() {
        super();
        setCode();
    }


    public ServiceException(String message) {
        super(message);
        setCode();
    }


    public ServiceException(String message, Throwable cause) {
        super(message, cause);
        setCode();
    }


    public ServiceException(Throwable cause) {
        super(cause);
        setCode();
    }


    @Override
    public String getMessage() {
        String message = super.getMessage();

        try {
            if (!Strings.isNullOrEmpty(message) && message.contains(":")) {
                message = message.substring(message.indexOf(":") + 2);
            }
        }
        catch (IndexOutOfBoundsException e) {
            LoggerFactory.getLogger(ServiceException.class).error(e.getMessage(), e);
        }

        return message;
    }


    public Integer generateCode() {
        byte[] classNameBytes = getClass().getName().getBytes();
        return CryptoUtil.hashBytes(classNameBytes);
    }


    private void setCode() {
        this.code = generateCode();
    }


    public Integer getCode() {
        return code;
    }


}
