package com.omarze.services.campaign;


import com.omarze.entities.Campaign;
import com.omarze.exception.ServiceException;
import com.omarze.persistence.CampaignRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

/**
 * created by julian
 */
@Component
@Transactional
public class CampaignActivationScheduler {

    private Logger logger = LoggerFactory.getLogger(CampaignActivationScheduler.class);


    private final CampaignRepository campaignRepository;


    public CampaignActivationScheduler(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }


    @Scheduled(fixedDelay = 86_400_000l)
    public void activateDueCampaigns() throws ServiceException {
        logger.info("Activating Campaigns");

        int page = 0;
        int size = 999;
        Page<Campaign> campaigns = campaignRepository
                .findCampaignsDueForActivation(PageRequest.of(page, size));


        while (!campaigns.isEmpty()) {


            campaignRepository.activateCampaigns(
                    campaigns.stream()
                            .map(Campaign::getId)
                            .collect(Collectors.toList())
            );

            campaigns = campaignRepository
                    .findCampaignsDueForActivation(PageRequest.of(++page, size));
        }
    }


    @Scheduled(fixedDelay = 86_400_000l)
    public void deactivateDueCampaigns() throws ServiceException {
        logger.info("Deactivating Campaigns");

        campaignRepository.deactivateAllDueCampaigns();
    }


}
