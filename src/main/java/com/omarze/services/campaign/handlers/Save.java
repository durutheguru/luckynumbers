package com.omarze.services.campaign.handlers;


import com.omarze.api.dto.CampaignDTO;
import com.omarze.entities.Campaign;
import com.omarze.entities.CampaignStatus;
import com.omarze.entities.RequestStatus;
import com.omarze.exception.ServiceException;
import com.omarze.persistence.CampaignRepository;
import com.omarze.services.Command;
import com.omarze.services.CommandBase;
import com.omarze.util.MapperUtil;
import com.omarze.util.ValidatorUtil;
import lombok.Builder;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

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

        campaign.setRequestStatus(RequestStatus.PENDING);
        campaign.setCampaignStatus(CampaignStatus.AWAITING_APPROVAL);
        campaign.setTimeAdded(LocalDateTime.now());

        return campaignRepository.save(campaign.initialize());
    }


}
