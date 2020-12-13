package com.julianduru.omarze.services.email;


import com.julianduru.omarze.entities.*;
import lombok.*;

import java.util.List;



/**
 * created by julian
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailRequest {


    private String sender;


    private String title;


    private List<String> recipients;


    private List<String> ccs;


    private String message;


    private EmailType emailType;



    public static EmailRequest fromEmail(Email email) {
        return EmailRequest.builder()
            .sender(email.getSender())
            .title(email.getTitle())
            .recipients(email.getRecipients())
            .ccs(email.getCcs())
            .message(email.getMessage())
            .emailType(email.getEmailType())
            .build();
    }


}
