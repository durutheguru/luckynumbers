package com.julianduru.omarze.services.campaign.handlers;


import com.julianduru.omarze.entities.Campaign;
import com.julianduru.omarze.exception.ServiceException;
import com.julianduru.omarze.persistence.CampaignRepository;
import com.julianduru.omarze.services.CommandBase;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.validation.constraints.NotNull;

/**
 * created by julian
 */
public class GetMatchingCampaign extends CommandBase<Page<Campaign>> {


    private final Campaign candidate;

    private final PageRequest pageRequest;

    @NotNull(message = "Campaign Repository must be set")
    private CampaignRepository campaignRepository;


    public GetMatchingCampaign(
        Campaign candidate, PageRequest pageRequest,
        CampaignRepository campaignRepository
    ) {
        this.candidate = candidate;
        this.pageRequest = pageRequest;
        this.campaignRepository = campaignRepository;
    }


    public GetMatchingCampaign setCampaignRepository(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
        return this;
    }


    @Override
    protected Page<Campaign> execute_() throws ServiceException {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("id")
                .withIgnoreNullValues();

        Example<Campaign> example = Example.of(candidate, matcher);

        return campaignRepository.findAll(example, pageRequest);
    }



}
