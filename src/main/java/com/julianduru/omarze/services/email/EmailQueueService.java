package com.julianduru.omarze.services.email;


import com.julianduru.omarze.config.properties.EmailConfig;
import lombok.RequiredArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.ConnectionFactory;

/**
 * created by julian
 */
@Component
@RequiredArgsConstructor
public class EmailQueueService extends RouteBuilder {


    private final EmailConfig emailConfig;


    private final EmailDispatcher emailDispatcher;


    @Override
    public void configure() throws Exception {
        from(String.format("timer:email_dispatcher?period=%s", emailConfig.getDispatchTimeout()))
            .bean(emailDispatcher, "fetchEmailsPendingDispatch")
            .log("Fetched Emails: ${body}")
            .to("jms:queue:EMAIL_DISPATCH")
            .bean(emailDispatcher, "updateQueuedEmails");
    }


}


