package com.julianduru.omarze.services.email;


import com.julianduru.omarze.api.dto.EmailDTO;
import com.julianduru.omarze.entities.Email;

import java.util.List;

/**
 * created by julian
 */
public interface EmailDispatcher {


    List<EmailDTO> fetchEmailsPendingDispatch();


    void updateQueuedEmails(List<EmailDTO> emails);


    void updateDispatchedEmails(List<EmailDTO> emails);


}
