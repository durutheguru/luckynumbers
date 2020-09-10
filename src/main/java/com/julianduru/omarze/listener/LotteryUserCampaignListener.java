package com.julianduru.omarze.listener;


import com.julianduru.omarze.event.LotteryUserCampaignEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * created by julian
 */
@Slf4j
@Component
public class LotteryUserCampaignListener {


    @EventListener({LotteryUserCampaignEvent.class})
    public void handleLotteryUserCampaignEvent(LotteryUserCampaignEvent event) throws Exception {
        log.info("Will dispatch User Lottery Number as soon as possible");
    }


}


