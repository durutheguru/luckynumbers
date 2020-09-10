package com.julianduru.omarze.listener;


import com.julianduru.omarze.event.CampaignActionEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * created by julian
 */
@Slf4j
@Component
public class CampaignActionListener {


    @EventListener({CampaignActionEvent.class})
    public void handleCampaignAction(CampaignActionEvent event) throws Exception {
        log.info("Will Send the Email as soon as I can...");
    }


}
