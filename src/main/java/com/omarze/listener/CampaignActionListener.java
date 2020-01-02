package com.omarze.listener;


import com.omarze.event.CampaignActionEvent;
import com.omarze.util.AppLogger;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * created by julian
 */
@Component
public class CampaignActionListener {


    @EventListener({CampaignActionEvent.class})
    public void handleCampaignAction(CampaignActionEvent event) throws Exception {
        AppLogger.info("Will Send the Email as soon as I can...");
    }


}
