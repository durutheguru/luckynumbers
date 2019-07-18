package com.omarze.persistence;


import com.omarze.entities.CampaignStageEvaluationResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * created by julian
 */
@Repository
public interface CampaignStageEvaluationResultRepository extends JpaRepository<CampaignStageEvaluationResult, Long> {

}
