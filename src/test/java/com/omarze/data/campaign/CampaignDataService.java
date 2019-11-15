package com.omarze.data.campaign;


import com.github.javafaker.Faker;
import com.omarze.data.partner.PartnerDataService;
import com.omarze.dto.CampaignDTO;
import com.omarze.entities.*;
import com.omarze.persistence.CampaignRepository;
import com.omarze.util.ObjectUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * created by julian
 */
@Component
public class CampaignDataService {

    final Faker faker;


    final ModelMapper modelMapper;


    final PartnerDataService partnerDataService;


    final CampaignRepository campaignRepository;


    public CampaignDataService(Faker faker, ModelMapper modelMapper, PartnerDataService partnerDataService, CampaignRepository campaignRepository) {
        this.faker = faker;
        this.modelMapper = modelMapper;
        this.partnerDataService = partnerDataService;
        this.campaignRepository = campaignRepository;
    }


    public CampaignDTO newCampaignDTO() {
        Campaign campaign = newCampaign();
        return modelMapper.map(campaign, CampaignDTO.class);
    }


    public Campaign newCampaign() {
        Partner partner = partnerDataService.savePartner();

        Campaign campaign = new Campaign();

        campaign.setName("Party with " + faker.name().fullName());
        campaign.setDescription("Come and " + campaign.getName());
        campaign.setStartDate(LocalDate.now());
        campaign.setEndDate(LocalDate.now().plusDays(5));
        campaign.setExpectedWinnerCount(1);
        campaign.setCampaignType(CampaignType.SINGLE);
        campaign.setStageDescriptions(campaignStageDescriptions());
        campaign.setPartner(partner);
        campaign.setRequestStatus(RequestStatus.PENDING);

        return campaign;
    }


    public Campaign saveCampaign() {
        Campaign campaign = newCampaign();
        return campaignRepository.save(campaign);
    }


    public Campaign saveCampaign(Campaign example) {
        Campaign campaign = newCampaign();
        ObjectUtil.copyProperties(example, campaign);

        return campaignRepository.save(campaign);
    }


    public List<Campaign> saveCampaigns(List<Campaign> campaignExamples) {
        return campaignExamples
                .stream()
                .map(this::saveCampaign)
                .collect(Collectors.toList());
    }


    public CampaignDTO saveCampaignDTO() {
        Campaign campaign = saveCampaign();
        return modelMapper.map(campaign, CampaignDTO.class);
    }


    public List<StageDescription> campaignStageDescriptions() {
        return Arrays.asList(
                new StageDescription()
                .setStage(Stage.FIRST)
                .setWinnersCount(faker.random().nextInt(5) + 1)
                .setEvaluationTime(LocalDateTime.now()),

                new StageDescription()
                .setStage(Stage.SECOND)
                .setWinnersCount(faker.random().nextInt(5) + 1)
                        .setEvaluationTime(LocalDateTime.now())
        );
    }


}
