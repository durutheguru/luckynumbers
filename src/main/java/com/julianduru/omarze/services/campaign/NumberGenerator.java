package com.julianduru.omarze.services.campaign;


import com.julianduru.omarze.entities.Campaign;
import com.julianduru.omarze.entities.LotteryUserCampaign;
import com.julianduru.omarze.persistence.LotteryUserCampaignRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * created by julian
 */
@Service
public class NumberGenerator {

    private static final Random random = new Random(System.currentTimeMillis());

    @Value("${lottery.config.numberLength:10}")
    private Integer numberSize;


    final LotteryUserCampaignRepository userCampaignRepository;


    public NumberGenerator(LotteryUserCampaignRepository userCampaignRepository) {
        this.userCampaignRepository = userCampaignRepository;
    }


    public boolean isExistingCampaignNumber(Campaign campaign, String number) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("id")
                .withIgnoreNullValues();

        Example<LotteryUserCampaign> userCampaignExample = Example.of(
                new LotteryUserCampaign(number, campaign), matcher
        );
        
        return userCampaignRepository.exists(userCampaignExample);
    }


    public String generateUniqueUserNumber(Campaign campaign) {
        String number;
        do {
            number = generateUserNumber();
        } while (isExistingCampaignNumber(campaign, number));

        return number;
    }


    public String generateUserNumber() {
        String number = "";

        for (int i = 1; i <= numberSize; i++) {
            number += random.nextInt(10);
        }

        return number;
    }


}
