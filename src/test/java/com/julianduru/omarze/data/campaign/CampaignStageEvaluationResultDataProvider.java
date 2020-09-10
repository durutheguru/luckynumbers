package com.julianduru.omarze.data.campaign;


import com.github.javafaker.Faker;
import com.julianduru.omarze.data.DataProvider;
import com.julianduru.omarze.entities.CampaignStageEvaluationResult;
import com.julianduru.omarze.entities.Stage;
import com.julianduru.omarze.persistence.CampaignStageEvaluationResultRepository;
import com.julianduru.util.NullAwareBeanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * created by julian
 */
@Component
@RequiredArgsConstructor
public class CampaignStageEvaluationResultDataProvider implements DataProvider<CampaignStageEvaluationResult> {


    private final Faker faker;

    private final CampaignDataProvider campaignDataProvider;

    private final CampaignStageEvaluationResultRepository stageEvaluationResultRepository;


    @Override
    public JpaRepository<CampaignStageEvaluationResult, Long> getRepository() {
        return stageEvaluationResultRepository;
    }

    @Override
    public CampaignStageEvaluationResult provide() {
        CampaignStageEvaluationResult result = new CampaignStageEvaluationResult();

        result.setStage(Stage.FIRST);
        result.setWinningNumbers(
            Arrays.asList(
                faker.code().isbn10(false),
                faker.code().isbn10(false)
            )
        );
        result.setCampaign(campaignDataProvider.save());

        return result;
    }

    @Override
    public CampaignStageEvaluationResult provide(CampaignStageEvaluationResult sample) {
        CampaignStageEvaluationResult result = provide();
        NullAwareBeanUtils.copy(sample, result);

        return result;
    }


}

