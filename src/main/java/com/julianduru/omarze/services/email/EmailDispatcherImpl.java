package com.julianduru.omarze.services.email;


import com.julianduru.omarze.api.dto.EmailDTO;
import com.julianduru.omarze.config.properties.EmailConfig;
import com.julianduru.omarze.entities.Email;
import com.julianduru.omarze.entities.EmailStatus;
import com.julianduru.omarze.persistence.EmailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * created by julian
 */
@RequiredArgsConstructor
@Component("emailDispatcher")
@Transactional
public class EmailDispatcherImpl implements EmailDispatcher {


    private final EmailConfig emailConfig;


    private final EmailRepository emailRepository;


    @Override
    public List<EmailDTO> fetchEmailsPendingDispatch() {
        Page<Email> emailPage = emailRepository.findByEmailStatusIn(
            Collections.singletonList(EmailStatus.PENDING_DISPATCH),

            PageRequest.of(0, emailConfig.getDispatchCount())
        );

        List<Email> emails = emailPage.getContent();
        return emails.stream().map(EmailDTO::fromEmail).collect(Collectors.toList());
    }


    @Override
    public void updateQueuedEmails(List<EmailDTO> emails) {
        updateEmailState(emails, EmailStatus.QUEUED);
    }


    @Override
    public void updateDispatchedEmails(List<EmailDTO> emails) {
        updateEmailState(emails, EmailStatus.DISPATCHED);
    }


    private void updateEmailState(List<EmailDTO> emails, EmailStatus emailStatus) {
        if (emails.isEmpty()) {
            return;
        }

        emailRepository.updateEmailStatusByReferences(
            emails
                .stream()
                .map(EmailDTO::getReference)
                .collect(Collectors.toList()),

            emailStatus
        );
    }


}
