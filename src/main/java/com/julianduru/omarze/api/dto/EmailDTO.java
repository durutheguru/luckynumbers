package com.julianduru.omarze.api.dto;


import com.julianduru.omarze.entities.Email;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * created by julian
 */
@Data
@NoArgsConstructor
public class EmailDTO implements Serializable {


    private static final long serialVersionUID = 6578895403089712888L;


    private String reference;


    private String sender;


    private String title;


    private String recipients;


    private String ccs;


    private String message;


    private String emailMimeType;


    public static EmailDTO fromEmail(Email email) {
        EmailDTO dto = new EmailDTO();

        dto.setReference(email.getReference());
        dto.setEmailMimeType(email.getEmailType().getMime());
        dto.setMessage(email.getMessage());
        dto.setTitle(email.getTitle());
        dto.setSender(email.getSender());
        dto.setRecipients(String.join(", ", email.getRecipients()));
        dto.setCcs(String.join(", ", email.getCcs()));

        return dto;
    }


}
