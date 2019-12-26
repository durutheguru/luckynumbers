package com.omarze.data.campaign;


import com.github.javafaker.Faker;
import com.omarze.data.partner.PartnerDataService;
import com.omarze.api.dto.CampaignDTO;
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

        return Campaign.builder()
                .name("Party with " + faker.name().fullName())
                .description("Come and ")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(5))
                .campaignType(CampaignType.SINGLE)
                .stageDescriptions(campaignStageDescriptions())
                .partner(partner)
                .build();
    }


    public CampaignDTO newInvalidStartEndDateCampaignDTO() {
        Campaign campaign = newInvalidStartEndDateCampaign();
        return modelMapper.map(campaign, CampaignDTO.class);
    }


    public Campaign newInvalidStartEndDateCampaign() {
        Partner partner = partnerDataService.savePartner();

        return Campaign.builder()
                .name("Party with " + faker.name().fullName())
                .description("Come and ")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(5))
                .campaignType(CampaignType.SINGLE)
                .stageDescriptions(invalidStartEndDateStageDescriptions())
                .partner(partner)
                .build();
    }


    public CampaignDTO newInvalidEvaluationTimeCampaignDTO() {
        Campaign campaign = newInvalidEvaluationTimeCampaign();
        return modelMapper.map(campaign, CampaignDTO.class);
    }





    public Campaign newInvalidEvaluationTimeCampaign() {
        Partner partner = partnerDataService.savePartner();

        return Campaign.builder()
                .name("Party with " + faker.name().fullName())
                .description("Come and ")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(5))
                .campaignType(CampaignType.SINGLE)
                .stageDescriptions(invalidEvaluationTimeStageDescriptions())
                .partner(partner)
                .build();
    }



    public CampaignDTO newInvalidWinnerCountCampaignDTO() {
        Campaign campaign = newInvalidWinnerCountCampaign();
        return modelMapper.map(campaign, CampaignDTO.class);
    }





    public Campaign newInvalidWinnerCountCampaign() {
        Partner partner = partnerDataService.savePartner();

        return Campaign.builder()
                .name("Party with " + faker.name().fullName())
                .description("Come and ")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(5))
                .campaignType(CampaignType.SINGLE)
                .stageDescriptions(invalidWinnerCountStageDescriptions())
                .partner(partner)
                .build();
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
            new StageDescription(
                Stage.FIRST,
                20,
                LocalDateTime.now()
            ),

            new StageDescription(
                Stage.SECOND,
                3,
                LocalDateTime.now()
            )
        );
    }



    public List<StageDescription> invalidStartEndDateStageDescriptions() {
        return Arrays.asList(
                new StageDescription(
                        Stage.FIRST,
                        faker.random().nextInt(5) + 1,
                        LocalDateTime.now().minusDays(10)
                ),

                new StageDescription(
                        Stage.SECOND,
                        faker.random().nextInt(5) + 1,
                        LocalDateTime.now().plusDays(10)
                )
        );
    }


    public List<StageDescription> invalidEvaluationTimeStageDescriptions() {
        return Arrays.asList(
            new StageDescription(
                Stage.FIRST,
                faker.random().nextInt(5) + 1,
                LocalDateTime.now().plusDays(10)
            ),

            new StageDescription(
                Stage.SECOND,
                faker.random().nextInt(5) + 1,
                LocalDateTime.now().minusDays(10)
            )
        );
    }


    public List<StageDescription> invalidWinnerCountStageDescriptions() {
        return Arrays.asList(
            new StageDescription(
                Stage.FIRST,
                15,
                LocalDateTime.now()
            ),

            new StageDescription(
                Stage.SECOND,
                30,
                LocalDateTime.now()
            )
        );
    }


}

