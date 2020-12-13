package com.julianduru.omarze.config.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;

/**
 * created by julian
 */
@Component
@Data
@Validated
@ConfigurationProperties(prefix = "lottery.config.email")
public class EmailConfig {


    @NotEmpty(message = "Default Email Sender should not be empty")
    private String defaultSender;


    private Long dispatchTimeout = 10000L;


    private Integer dispatchCount = 100;


}

