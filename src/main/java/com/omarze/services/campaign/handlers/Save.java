package com.omarze.services.campaign.handlers;


import com.omarze.api.dto.CampaignDTO;
import com.omarze.entities.Campaign;
import com.omarze.entities.CampaignStatus;
import com.omarze.entities.Stage;
import com.omarze.entities.StageDescription;
import com.omarze.exception.InvalidObjectException;
import com.omarze.exception.ServiceException;
import com.omarze.persistence.CampaignRepository;
import com.omarze.services.CommandBase;
import com.omarze.util.MapperUtil;
import lombok.Builder;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

/**
 * created by julian
 */
public class Save extends CommandBase<Campaign> {


    @NotNull(message = "Campaign Object is required")
    private final CampaignDTO campaignDto;


    @NotNull
    private final CampaignRepository campaignRepository;


    @Builder
    private Save(CampaignDTO campaignDto, CampaignRepository campaignRepository) {
        this.campaignDto = campaignDto;
        this.campaignRepository = campaignRepository;
    }


    @Override
    protected Campaign execute_() throws ServiceException {
        Campaign campaign = MapperUtil.map(campaignDto, Campaign.class);

        campaign.setCampaignStatus(CampaignStatus.AWAITING_APPROVAL);
        campaign.setTimeAdded(LocalDateTime.now());

        return campaignRepository.save(campaign.initialize());
    }


}


