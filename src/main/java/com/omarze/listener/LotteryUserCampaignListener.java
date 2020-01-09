package com.omarze.listener;


import com.omarze.event.LotteryUserCampaignEvent;
import com.omarze.util.AppLogger;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * created by julian
 */
@Component
public class LotteryUserCampaignListener {


    @EventListener({LotteryUserCampaignEvent.class})
    public void handleLotteryUserCampaignEvent(LotteryUserCampaignEvent event) throws Exception {
        AppLogger.info("Will dispatch User Lottery Number as soon as possible");
    }


}


