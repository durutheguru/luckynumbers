package com.julianduru.omarze.listener;


import com.julianduru.omarze.entities.Campaign;
import com.julianduru.omarze.event.NewCampaignEvent;
import com.julianduru.omarze.persistence.BackOfficeUserRepository;
import com.julianduru.webpush.api.NotificationPayload;
import com.julianduru.webpush.api.NotificationPayloadSerializer;
import com.julianduru.webpush.event.NotificationEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * created by julian
 */
@Component
@RequiredArgsConstructor
public class NewCampaignListener {


    private final static String NEW_CAMPAIGN_NOTIFICATION_TYPE = "NEW_CAMPAIGN_NOTIFICATION";


    private final ApplicationEventPublisher eventPublisher;


    private final BackOfficeUserRepository backOfficeUserRepository;


    private final NotificationPayloadSerializer payloadSerializer;


    @EventListener
    public void handleNewCampaign(NewCampaignEvent event) {
        Campaign campaign = (Campaign) event.getData();
        String campaignCreator = campaign.getCreatedBy().authUsername;

        Set<String> userIds = backOfficeUserRepository
            .findByUsernameNot(campaignCreator)
            .stream()
            .map(BackOfficeUserRepository.UsernameProjection::getUsername)
            .collect(Collectors.toSet());

        String message = payloadSerializer.serialize(
            NotificationPayload.<String>builder()
                .data(campaignCreator)
                .message(String.format("New Campaign Request by %s", campaignCreator))
                .type(NEW_CAMPAIGN_NOTIFICATION_TYPE)
                .build()
        );

        NotificationEvent notificationEvent = new NotificationEvent(userIds, message);

        eventPublisher.publishEvent(notificationEvent);
    }


}

