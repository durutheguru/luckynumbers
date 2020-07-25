package com.omarze.services.campaign.handlers;


import com.julianduru.util.MapperUtil;
import com.omarze.api.dto.CampaignDTO;
import com.omarze.entities.Campaign;
import com.omarze.entities.CampaignStatus;
import com.omarze.entities.Partner;
import com.omarze.exception.EntityNotFoundException;
import com.omarze.exception.ServiceException;
import com.omarze.persistence.CampaignRepository;
import com.omarze.persistence.PartnerRepository;
import com.omarze.services.CommandBase;
import lombok.Builder;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

/**
 * created by julian
 */
public class Save extends CommandBase<Campaign> {


    @NotNull(message = "Campaign Object is required")
    private final CampaignDTO campaignDto;


    @NotNull
    private final CampaignRepository campaignRepository;


    @NotNull
    private final PartnerRepository partnerRepository;


    @Builder
    private Save(CampaignDTO campaignDto, CampaignRepository campaignRepository, PartnerRepository partnerRepository) {
        this.campaignDto = campaignDto;
        this.campaignRepository = campaignRepository;
        this.partnerRepository = partnerRepository;
    }


    @Override
    protected Campaign execute_() throws ServiceException {
        Campaign campaign = MapperUtil.map(campaignDto, Campaign.class);

        campaign.setPartner(getPartner());
        campaign.setCampaignStatus(CampaignStatus.AWAITING_APPROVAL);
        campaign.setTimeAdded(ZonedDateTime.now());

        return campaignRepository.save(campaign.initialize());
    }


    private Partner getPartner() throws EntityNotFoundException {
        return partnerRepository
                .findById(campaignDto.getPartnerId())
                .orElseThrow(() -> new EntityNotFoundException("Partner", campaignDto.getPartnerId()));
    }


}


