package com.omarze.data.campaign;


import com.github.javafaker.Faker;
import com.omarze.data.TestDataProvider;
import com.omarze.data.partner.PartnerDataProvider;
import com.omarze.api.dto.CampaignDTO;
import com.omarze.entities.*;
import com.omarze.exception.InvalidObjectException;
import com.omarze.persistence.CampaignRepository;
import com.omarze.util.AppLogger;
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
public class CampaignDataProvider extends TestDataProvider<Campaign, CampaignRepository> {


    final Faker faker;


    final ModelMapper modelMapper;


    final PartnerDataProvider partnerDataProvider;


    public CampaignDataProvider(
        Faker faker,
        ModelMapper modelMapper,
        PartnerDataProvider partnerDataProvider,
        CampaignRepository campaignRepository
    ) {
        super(campaignRepository);

        this.faker = faker;
        this.modelMapper = modelMapper;
        this.partnerDataProvider = partnerDataProvider;
    }


    public CampaignDTO newCampaignDTO() {
        Campaign campaign = newEntity();
        CampaignDTO campaignDTO = modelMapper.map(campaign, CampaignDTO.class);

        return campaignDTO;
    }


    @Override
    public Campaign newEntity() {
        List<Partner> partners = partnerDataProvider.loadPersistedEntities();

        Partner partner = partners.isEmpty() ? partnerDataProvider.saveEntity() : partners.get(0);

        Campaign campaign = new Campaign();

        campaign.setName("Party with " + faker.name().fullName());
        campaign.setDescription("Come and ");
        campaign.setStartDate(LocalDate.now());
        campaign.setEndDate(LocalDate.now().plusDays(5));
        campaign.setCampaignType(CampaignType.SINGLE);
        campaign.setStageDescriptions(campaignStageDescriptions());
        campaign.setCampaignStatus(CampaignStatus.AWAITING_APPROVAL);
        campaign.setExpectedWinnerCount(3);
        campaign.setPartner(partner);

        try {
            return campaign.initialize();
        }
        catch (InvalidObjectException e) {
            AppLogger.error(e);
            return campaign;
        }
    }


    public Campaign newActiveCampaign() {
        Partner partner = partnerDataProvider.saveEntity();

        Campaign campaign = new Campaign();

        campaign.setName("Party with " + faker.name().fullName());
        campaign.setDescription("Come and ");
        campaign.setStartDate(LocalDate.now().minusDays(5));
        campaign.setEndDate(LocalDate.now());
        campaign.setCampaignType(CampaignType.SINGLE);
        campaign.setStageDescriptions(activeCampaignStageDescriptions());
        campaign.setCampaignStatus(CampaignStatus.ACTIVE);
        campaign.setExpectedWinnerCount(3);
        campaign.setPartner(partner);

        try {
            return campaign.initialize();
        }
        catch (InvalidObjectException e) {
            AppLogger.error(e);
            return campaign;
        }
    }


    public CampaignDTO newInvalidStartEndDateCampaignDTO() {
        Campaign campaign = newInvalidStartEndDateCampaign();
        return modelMapper.map(campaign, CampaignDTO.class);
    }


    public Campaign newInvalidStartEndDateCampaign() {
        Partner partner = partnerDataProvider.saveEntity();

        Campaign campaign = new Campaign();

        campaign.setName("Party with " + faker.name().fullName());
        campaign.setDescription("Come and ");
        campaign.setStartDate(LocalDate.now());
        campaign.setEndDate(LocalDate.now().plusDays(5));
        campaign.setCampaignType(CampaignType.SINGLE);
        campaign.setStageDescriptions(invalidStartEndDateStageDescriptions());
        campaign.setPartner(partner);

        return campaign;
    }


    public CampaignDTO newInvalidEvaluationTimeCampaignDTO() {
        Campaign campaign = newInvalidEvaluationTimeCampaign();
        return modelMapper.map(campaign, CampaignDTO.class);
    }


    public Campaign newInvalidEvaluationTimeCampaign() {
        Partner partner = partnerDataProvider.saveEntity();

        Campaign campaign = new Campaign();

        campaign.setName("Party with " + faker.name().fullName());
        campaign.setDescription("Come and ");
        campaign.setStartDate(LocalDate.now());
        campaign.setEndDate(LocalDate.now().plusDays(5));
        campaign.setCampaignType(CampaignType.SINGLE);
        campaign.setStageDescriptions(invalidEvaluationTimeStageDescriptions());
        campaign.setPartner(partner);

        return campaign;
    }


    public CampaignDTO newInvalidWinnerCountCampaignDTO() {
        Campaign campaign = newInvalidWinnerCountCampaign();
        return modelMapper.map(campaign, CampaignDTO.class);
    }


    public Campaign newInvalidWinnerCountCampaign() {
        Partner partner = partnerDataProvider.saveEntity();

        Campaign campaign = new Campaign();

        campaign.setName("Party with " + faker.name().fullName());
        campaign.setDescription("Come and ");
        campaign.setStartDate(LocalDate.now());
        campaign.setEndDate(LocalDate.now().plusDays(5));
        campaign.setCampaignType(CampaignType.SINGLE);
        campaign.setStageDescriptions(invalidWinnerCountStageDescriptions());
        campaign.setPartner(partner);

        return campaign;
    }


    @Override
    public Campaign saveEntity() {
        Campaign campaign = newEntity();
        return getRepository().save(campaign);
    }


    public Campaign saveActiveCampaign() {
        Campaign campaign = newActiveCampaign();
        return getRepository().save(campaign);
    }


    public Campaign saveEntity(Campaign example) {
        Campaign campaign = newEntity();
        ObjectUtil.copyProperties(example, campaign);

        try {
            campaign.initialize();
        }
        catch (InvalidObjectException e) {
            AppLogger.error(e);
        }

        return getRepository().save(campaign);
    }


    public List<Campaign> saveCampaigns(List<Campaign> campaignExamples) {
        return campaignExamples
                .stream()
                .map(this::saveEntity)
                .collect(Collectors.toList());
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


    public List<StageDescription> activeCampaignStageDescriptions() {
        return Arrays.asList(
                new StageDescription(
                        Stage.FIRST,
                        20,
                        LocalDateTime.now().minusDays(5)
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

